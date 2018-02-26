package co.ceiba.parking.domain;

import java.util.Calendar;
import java.util.Date;

import co.ceiba.parking.domain.excepcion.ParkingException;
import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.services.InvoiceService;
import co.ceiba.parking.persistent.services.RegisterService;
import co.ceiba.parking.persistent.services.ServicesPersistent;
import co.ceiba.parking.persistent.services.VehicleService;
import co.ceiba.parking.services.DateServiceInterface;

public class Vigilant {
	private VehicleService vehicleService;
	private RegisterService registerService;
	private InvoiceService invoiceService;
	
	private DateServiceInterface dateServiceInterfaces;
	
	public Vigilant(DateServiceInterface dateServiceInterfaces, ServicesPersistent servicesPersistent) {
		vehicleService = servicesPersistent.getVehicleService();
		invoiceService = servicesPersistent.getInvoiceService();
		registerService = servicesPersistent.getRegisterService();
		
		this.dateServiceInterfaces = dateServiceInterfaces;
	}
	
	public String input(Vehicle vehicle) {
		Date ingreso = dateServiceInterfaces.dateNow();
		
		if(vehicle.getPlaca().isEmpty()) {
			throw new ParkingException(Messages.DEBE_INGRESAR_PLACA);
		}
		
		if(vehicle.getTipo().isEmpty()) {
			throw new ParkingException(Messages.DEBE_INGRESAR_TIPO_VEHICULO);
		}
		
		Vehicle tmp = vehicleLoad(vehicle);
		if(tmp == null) {
			vehicle = vehicleCreate(vehicle);
		}else {
			vehicle = tmp;
		}
  	
		if(!isAuthorized(vehicle, ingreso)) {
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
	
	public Invoice output(Vehicle vehicle) {
		Date salida = dateServiceInterfaces.dateNow();

		vehicle = getVehicle(vehicle.getPlaca());
		
		if(vehicle == null) {
			throw new ParkingException(Messages.VEHICULO_NO_EXISTE);
		}
		
		if(!isHere(vehicle)) {
			throw new ParkingException(Messages.NO_A_INGRESADO);
		}

		Conditions conditions = Conditions.get(vehicle);
		Register register = setOutVehicle(vehicle, salida);
		
		Calculator calculator = new Calculator(conditions, register);
		
		Time time = calculator.time();
		Cost cost = calculator.cost(time);
		
		return createInvoice(register, time, cost);
	}
	
	private boolean isAuthorized(Vehicle vehicle, Date date) {
		return (!vehicle.getPlaca().startsWith("A") || plateAIsDayAuthorized(date));
	}

	private boolean plateAIsDayAuthorized(Date date) {
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
	
	private Vehicle vehicleLoad(Vehicle vehicle) {
		return getVehicle(vehicle.getPlaca());
	}
	
	private Vehicle vehicleCreate(Vehicle vehicle) {
		return vehicle.persist(vehicleService);
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
}
