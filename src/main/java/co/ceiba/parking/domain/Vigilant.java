package co.ceiba.parking.domain;

import java.util.Calendar;
import java.util.Date;

import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.service.InvoiceService;
import co.ceiba.parking.persistent.service.RegisterService;
import co.ceiba.parking.persistent.service.RegistryAdmittedService;
import co.ceiba.parking.persistent.service.ServicesPersistent;
import co.ceiba.parking.persistent.service.VehicleService;

public class Vigilant {
	private VehicleService vehicleService;
	private RegistryAdmittedService registryAdmittedService;
	private RegisterService registerService;
	private InvoiceService invoiceService;
	private String msg;
	
	public Vigilant(ServicesPersistent servicesPersistent) {
		vehicleService = servicesPersistent.getVehicleService();
		registryAdmittedService = servicesPersistent.getRegistryAdmittedService();
		invoiceService = servicesPersistent.getInvoiceService();
		registerService = servicesPersistent.getRegisterService();
		msg = "";
	}
	
	public String ingreso(String tipo, String placa, int cilindraje) {
		Date ingreso = dateNow();
		
		
		Vehicle vehicle = createVehicle(tipo, placa, cilindraje);
  	
		if(!isAutorized(vehicle, ingreso)) {
			msg = Messages.INGRESO_NO_AUTORIZADO;
			return msg;
		}
		if(isHere(vehicle)) {
			msg = Messages.YA_HABIA_INGRESADO;
			return msg;
		}
		if(!thereCapacity(vehicle)) {
			msg = Messages.NO_HAY_CUPO;
			return msg;
		}

		createRegistryAdmitted(vehicle, ingreso);
		msg = Messages.INGRESO_SATISFACTORIO;
		
		return getMsg();
	}
	
	public String salida(Vehicle vehicle) {
		Date salida = dateNow();
		
		if(!isHere(vehicle)) {
			msg = Messages.NO_A_INGRESADO;
			return null;
		}

		Conditions conditions = Conditions.get(vehicle);
		Register register = createRegister(vehicle, salida);
		Time time = new Time(conditions, register).calculate();
		Cost cost = new Cost(time).calculate();
		
		createInvoice(register, time, cost);
		return getMsg();
	}
	
	public boolean isAutorized(Vehicle vehicle, Date date) {
		String placa = vehicle.getPlaca().toLowerCase();
		if (placa.startsWith("a")) {
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

	public boolean isHere(Vehicle vehicle) {
		RegistryAdmitted admitted = registryAdmittedService.findByVehicle(vehicle);
		return (admitted != null);
	}
	
	public boolean thereCapacity(Vehicle vehicle) {
		Conditions conditions = Conditions.get(vehicle);
		int count = registryAdmittedService.countByTypeVehicle(vehicle);
		return count < conditions.getCupo();
	}
	
	public Vehicle createVehicle(String tipo, String placa, int cilindraje) {
		Vehicle vehicle = vehicleService.findByTipoAndPlaca(tipo, placa);
  	if(vehicle == null) {
  		vehicle = new Vehicle(
  				tipo,
  				placa,
  				cilindraje
  				).persist(vehicleService);
  	}
  	return vehicle;
	}
	
	public RegistryAdmitted createRegistryAdmitted(Vehicle vehicle, Date date) {
		return new RegistryAdmitted(
				vehicle,
				date
				).persist(registryAdmittedService);
	}
	
	public Register createRegister(Vehicle vehicle, Date salida) {
		RegistryAdmitted admitted = registryAdmittedService.findByVehicle(vehicle);
		return new Register(
				admitted.getVehicle(),
				admitted.getIngreso(),
				salida
				).persist(registerService);
	}
	
	public Invoice createInvoice(Register register, Time time, Cost cost) {
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

	public String getMsg() {
		return msg;
	}
	
	protected Date dateNow() {
		return new Date();
	}
}
