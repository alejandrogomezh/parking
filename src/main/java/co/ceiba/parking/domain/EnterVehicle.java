package co.ceiba.parking.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.service.persistent.RegistryAdmittedService;
import co.ceiba.parking.service.persistent.ServicesPersistent;

public class EnterVehicle {
	private RegistryAdmittedService registryAdmittedService;
	
	private String msg;

	public EnterVehicle(ServicesPersistent servicesPersistent) {
		this.registryAdmittedService = servicesPersistent.getRegistryAdmittedService();
		msg = "";
	}

	public RegistryAdmitted enter(Date date, Vehicle vehicle) {
		msg = "";
		if(!isAutorized(vehicle, date)) {
			msg = Messages.INGRESO_NO_AUTORIZADO;
			return null;
		}
		if(isHere(vehicle)) {
			msg = Messages.YA_HABIA_INGRESADO;
			return null;
		}
		if(!thereCapacity(vehicle)) {
			msg = Messages.NO_HAY_CUPO;
			return null;
		}
		RegistryAdmitted registryAdmitted = new RegistryAdmitted(vehicle, date)
				.persist(registryAdmittedService);
		msg = Messages.INGRESO_SATISFACTORIO;
		return  registryAdmitted;
	}
	
	private boolean isAutorized(Vehicle vehicle, Date date) {
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

	private boolean isHere(Vehicle vehicle) {
		List<RegistryAdmitted> ingresos = registryAdmittedService.findByVehicle(vehicle);
		return !ingresos.isEmpty();
	}
	
	private boolean thereCapacity(Vehicle vehicle) {
		Conditions conditions = Conditions.get(vehicle);
		return registryAdmittedService.countByTypeVehicle(vehicle.getTipo()) < conditions.getCupo();
	}
		
	public String getMsg() {
		return msg;
	}

}
