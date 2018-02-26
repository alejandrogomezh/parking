package co.ceiba.parking.domain.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.ceiba.parking.persistent.services.VehicleService;

@JsonInclude(Include.NON_NULL)
public class Vehicle{
	
	private String tipo;
	private String placa;
	private Integer cilindraje;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String tipo, String placa, Integer cilindraje) {
		this.tipo = tipo;
		this.placa = placa.toUpperCase();
		this.cilindraje = cilindraje;
	}
	
	public String getPlaca() {
		return placa.toUpperCase();
	}
	
	public Integer getCilindraje() {
		return cilindraje;
	}

	public String getTipo() {
		return tipo;
	}
	
	@JsonIgnore
	private Object selfEntity;
	public Object getSelfEntity() {
		return selfEntity;
	}
	public Vehicle setSelfEntity(Object entity) {
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
	
}
