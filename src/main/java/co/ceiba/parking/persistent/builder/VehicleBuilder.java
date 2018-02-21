package co.ceiba.parking.persistent.builder;

import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.entities.VehicleEntity;

public class VehicleBuilder {
	
	private VehicleBuilder() {
		
	}
	
	public static Vehicle toDomain(VehicleEntity vehicleEntity) {
		Vehicle vehicle = null;
		if(vehicleEntity != null) {
			vehicle = new Vehicle(
					vehicleEntity.getTipo(),
					vehicleEntity.getPlaca(),
					vehicleEntity.getCilindraje()
					);
			vehicle.setSelfEntity(vehicleEntity);
		}
		return vehicle;
	}
	
	public static VehicleEntity toEntity(Vehicle vehicle) {
		if(vehicle.getSelfEntity() != null) return vehicle.getSelfEntity();
		VehicleEntity vehicleEntity = null;
		vehicleEntity = new VehicleEntity();
		vehicleEntity.setTipo(vehicle.getTipo());
		vehicleEntity.setPlaca(vehicle.getPlaca());
		vehicleEntity.setCilindraje(vehicle.getCilindraje());
		return vehicleEntity;
	}
	
}
