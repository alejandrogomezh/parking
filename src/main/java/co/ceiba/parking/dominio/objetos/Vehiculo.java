package co.ceiba.parking.dominio.objetos;

import co.ceiba.parking.dominio.repositorio.VehiculoRepositorio;
import co.ceiba.parking.persistencia.entidad.VehiculoEntity;

public class Vehiculo {
	private String tipo;
	private String placa;
	private int cilindraje;
	
	public Vehiculo(String tipo, String placa, int cilindraje) {
		this.tipo = tipo;
		this.placa = placa;
		this.cilindraje = cilindraje;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public int getCilindraje() {
		return cilindraje;
	}

	public String getTipo() {
		return tipo;
	}
	
	private VehiculoEntity selfEntity;
	public VehiculoEntity getSelfEntity() {
		return selfEntity;
	}
	public Vehiculo setSelfEntity(VehiculoEntity entity) {
		this.selfEntity = entity;
		return this;
	}
	
	public Vehiculo persistente(VehiculoRepositorio vehiculoRepositorio) {
		Vehiculo self = vehiculoRepositorio.obtener(this);
		if(self.selfEntity == null) {
			self = vehiculoRepositorio.agregar(this);
		}
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}
	
}
