package co.ceiba.parking.domain.objects;

import co.ceiba.parking.persistent.entities.VehicleEntity;
import co.ceiba.parking.persistent.service.VehicleService;

public class Vehicle implements java.io.Serializable{
	private static final long serialVersionUID = -7644302969449909424L;
	
	private String tipo;
	private String placa;
	private int cilindraje;
	
	public Vehicle(String tipo, String placa, int cilindraje) {
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
	
	private VehicleEntity selfEntity;
	public VehicleEntity getSelfEntity() {
		return selfEntity;
	}
	public Vehicle setSelfEntity(VehicleEntity entity) {
		this.selfEntity = entity;
		return this;
	}
	
	public Vehicle persist(VehicleService vehicleService) {
		Vehicle self = vehicleService.findByPlaca(placa);
		if(self == null) {
			self = vehicleService.save(this);
		}
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}

	@Override
	public String toString() {
		return "Vehicle [tipo=" + tipo + ", placa=" + placa + ", cilindraje=" + cilindraje + "]";
	}
	
}
