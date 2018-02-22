package co.ceiba.parking.domain.objects;

import java.util.Date;

import co.ceiba.parking.persistent.entities.RegisterEntity;
import co.ceiba.parking.persistent.service.RegisterService;

public class Register implements java.io.Serializable{
	private static final long serialVersionUID = 3624261432628024680L;
	
	private Vehicle vehicle;
	private Date ingreso;
	private Date salida;
	
	public Register(Vehicle vehicle, Date ingreso) {
		this.vehicle = vehicle;
		this.ingreso = ingreso;
		this.salida = null;
	}
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
	
	public void setSalida(Date salida) {
		this.salida = salida;
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
	@Override
	public String toString() {
		return "Register [vehicle=" + vehicle + ", ingreso=" + ingreso + ", salida=" + salida + "]";
	}
}
