package co.ceiba.parking.domain;

import java.util.Date;

import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.service.persistent.ServicesPersistent;

public class Vigilant {
	private EnterVehicle enterVehicle;
	private OutVehicle outVehicle;
	
	public Vigilant(ServicesPersistent servicesPersistent) {
		enterVehicle = new EnterVehicle(servicesPersistent);
		outVehicle = new OutVehicle(servicesPersistent);
	}
	
	public String ingreso(Vehicle vehicle) {
		enterVehicle.enter(fechaActual(), vehicle);
		return enterVehicle.getMsg();
	}
	
	public String salida(Vehicle vehicle) {
		outVehicle.out(fechaActual(), vehicle);
		return outVehicle.getMsg();
	}
	
	protected Date fechaActual() {
		return new Date();
	}
}
