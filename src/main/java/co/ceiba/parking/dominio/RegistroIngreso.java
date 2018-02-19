package co.ceiba.parking.dominio;

import java.util.Date;

import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.dominio.repositorio.RegistroIngresoRepositorio;
import co.ceiba.parking.persistencia.entidad.RegistroIngresoEntity;

public class RegistroIngreso {
	private Vehiculo vehiculo;
	private Date ingreso;
	
	public RegistroIngreso(Vehiculo vehiculo, Date ingreso) {
		this.vehiculo = vehiculo;
		this.ingreso = ingreso;
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
	public Date getIngreso() {
		return ingreso;
	}
	
	private RegistroIngresoEntity selfEntity;
	public RegistroIngresoEntity getSelfEntity() {
		return selfEntity;
	}
	public RegistroIngreso setSelfEntity(RegistroIngresoEntity entity) {
		this.selfEntity = entity;
		return this;
	}
	
	public RegistroIngreso persistente(RegistroIngresoRepositorio registroIngresoRepositorio) {
		RegistroIngreso self = registroIngresoRepositorio.agregar(this);
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}
}
