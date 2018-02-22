package co.ceiba.parking.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.ceiba.parking.domain.excepcion.ParkingException;
import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.service.InvoiceService;
import co.ceiba.parking.persistent.service.RegisterService;
import co.ceiba.parking.persistent.service.ServicesPersistent;
import co.ceiba.parking.persistent.service.VehicleService;

public class Vigilant {
	private VehicleService vehicleService;
	private RegisterService registerService;
	private InvoiceService invoiceService;
	
	public Vigilant(ServicesPersistent servicesPersistent) {
		vehicleService = servicesPersistent.getVehicleService();
		invoiceService = servicesPersistent.getInvoiceService();
		registerService = servicesPersistent.getRegisterService();
	}
	
	public String ingreso(String tipo, String placa, int cilindraje) {
		Date ingreso = dateNow();
		placa = placa.toUpperCase();
		
		Vehicle vehicle = createVehicle(tipo, placa, cilindraje);
  	
		if(!isAutorized(vehicle, ingreso)) {
			throw new ParkingException(Messages.INGRESO_NO_AUTORIZADO);
		}
		if(isHere(vehicle)) {
			throw new ParkingException(Messages.YA_HABIA_INGRESADO);
		}
		if(!thereCapacity(vehicle)) {
			throw new ParkingException(Messages.NO_HAY_CUPO);
		}

		setInVehicle(vehicle, ingreso);
		return Messages.INGRESO_SATISFACTORIO;
	}
	
	public String salida(String placa) {
		Date salida = dateNow();
		placa = placa.toUpperCase();
		
		Vehicle vehicle = getVehicle(placa);
		
		if(vehicle == null) {
			throw new ParkingException(Messages.VEHICULO_NO_EXISTE);
		}
		
		if(!isHere(vehicle)) {
			throw new ParkingException(Messages.NO_A_INGRESADO);
		}

		Conditions conditions = Conditions.get(vehicle);
		Register register = setOutVehicle(vehicle, salida);
		Time time = new Time(conditions, register).calculate();
		Cost cost = new Cost(time).calculate();
		
		createInvoice(register, time, cost);
		return Messages.SALIDA_SATISFACTORIO;
	}
	
	public List<Register> getAdmittedList() {
		return registerService.allVehiclesActives();
	}
	
	private boolean isAutorized(Vehicle vehicle, Date date) {
		String placa = vehicle.getPlaca();
		if (placa.startsWith("A")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			switch (calendar.get(Calendar.DAY_OF_WEEK)) {
				case Calendar.SATURDAY:
					return false;
				case Calendar.SUNDAY:
					return false;
				default:
					return true;
			}
		}
		return true;
	}

	private boolean isHere(Vehicle vehicle) {
		Register admitted = registerService.findByVehicleActive(vehicle);
		return (admitted != null);
	}
	
	private boolean thereCapacity(Vehicle vehicle) {
		Conditions conditions = Conditions.get(vehicle);
		int count = registerService.countByTypeVehicle(vehicle);
		return count < conditions.getCupo();
	}
	
	private Vehicle getVehicle(String placa) {
		return vehicleService.findByPlaca(placa);
	}
	
	private Vehicle createVehicle(String tipo, String placa, int cilindraje) {
		Vehicle vehicle = vehicleService.findByPlaca(placa);
  	if(vehicle == null) {
  		vehicle = new Vehicle(
  				tipo,
  				placa,
  				cilindraje
  				).persist(vehicleService);
  	}
  	return vehicle;
	}
	
	private Register setInVehicle(Vehicle vehicle, Date input) {
		return new Register(
				vehicle,
				input
				).persist(registerService);
	}
	
	private Register setOutVehicle(Vehicle vehicle, Date output) {
		Register admitted = registerService.findByVehicleActive(vehicle);
		admitted.setSalida(output);
		registerService.save(admitted);
		return admitted;
	}
	
	private Invoice createInvoice(Register register, Time time, Cost cost) {
		return new Invoice(
				register,
				time.getDias(),
				time.getHoras(),
				cost.getValorDias(),
				cost.getValorHoras(),
				cost.getValorRecargo(),
				cost.getValorTotal()	
				).persist(invoiceService);
	}

	protected Date dateNow() {
		return new Date();
	}
}
