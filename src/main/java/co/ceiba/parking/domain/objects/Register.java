package co.ceiba.parking.domain.objects;

import java.util.Date;

import co.ceiba.parking.persistent.entities.RegisterEntity;
import co.ceiba.parking.persistent.service.RegisterService;

public class Register {
	private Vehicle vehicle;
	private Date ingreso;
	private Date salida;
	
	public Register(Vehicle vehicle, Date ingreso, Date salida) {
		this.vehicle = vehicle;
		this.ingreso = ingreso;
		this.salida = salida;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public Date getIngreso() {
		return ingreso;
	}
	
	public Date getSalida() {
		return salida;
	}
	
	private RegisterEntity selfEntity;
	public RegisterEntity getSelfEntity() {
		return selfEntity;
	}
	public Register setSelfEntity(RegisterEntity selfEntity) {
		this.selfEntity = selfEntity;
		return this;
	}
	
	public Register persist(RegisterService registerService) {
		Register self = registerService.save(this);
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}
}
