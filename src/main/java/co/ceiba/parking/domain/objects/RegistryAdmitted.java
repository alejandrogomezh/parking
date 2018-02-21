package co.ceiba.parking.domain.objects;

import java.util.Date;

import co.ceiba.parking.persistent.entities.RegistryAdmittedEntity;
import co.ceiba.parking.persistent.service.RegistryAdmittedService;


public class RegistryAdmitted {	
	private Vehicle vehicle;
	private Date ingreso;
	
	public RegistryAdmitted(Vehicle vehicle, Date ingreso) {
		this.vehicle = vehicle;
		this.ingreso = ingreso;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public Date getIngreso() {
		return ingreso;
	}
	
	private RegistryAdmittedEntity selfEntity;
	public RegistryAdmittedEntity getSelfEntity() {
		return selfEntity;
	}
	public RegistryAdmitted setSelfEntity(RegistryAdmittedEntity selfEntity) {
		this.selfEntity = selfEntity;
		return this;
	}
	
	public RegistryAdmitted persist(RegistryAdmittedService registryAdmittedService) {
		RegistryAdmitted self = registryAdmittedService.save(this);
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}

	
}
