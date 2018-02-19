package co.ceiba.parking.dominio.objetos;

import java.util.Date;

import co.ceiba.parking.dominio.repositorio.RegistroRepositorio;
import co.ceiba.parking.persistencia.entidad.RegistroEntity;

public class Registro {
	private Vehiculo vehiculo;
	private Date ingreso;
	private Date salida;
	
	public Registro(Vehiculo vehiculo, Date ingreso, Date salida) {
		this.vehiculo = vehiculo;
		this.ingreso = ingreso;
		this.salida = salida;
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
	public Date getIngreso() {
		return ingreso;
	}
	
	public Date getSalida() {
		return salida;
	}
	
	private RegistroEntity selfEntity;
	public RegistroEntity getSelfEntity() {
		return selfEntity;
	}
	public Registro setSelfEntity(RegistroEntity selfEntity) {
		this.selfEntity = selfEntity;
		return this;
	}
	
	public Registro persistente(RegistroRepositorio registroRepositorio) {
		Registro self = registroRepositorio.agregar(this);
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}
}
