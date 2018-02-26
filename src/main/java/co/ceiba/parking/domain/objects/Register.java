package co.ceiba.parking.domain.objects;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import co.ceiba.parking.persistent.services.RegisterService;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Register{
	
	private Vehicle vehicle;
	private Date ingreso;
	private Date salida;
	
	public Register() {
		
	}
	
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

	@JsonIgnore
	private Object selfEntity;
	public Object getSelfEntity() {
		return selfEntity;
	}
	public Register setSelfEntity(Object selfEntity) {
		this.selfEntity = selfEntity;
		return this;
	}
	
	public Register persist(RegisterService registerService) {
		Register self = registerService.save(this);
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}

}
