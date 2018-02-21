package co.ceiba.parking.persistent.builder;

import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.entities.RegistryAdmittedEntity;
import co.ceiba.parking.persistent.entities.VehicleEntity;

public class RegistryAdmittedBuilder {

	private RegistryAdmittedBuilder() {
		
	}
	
	public static RegistryAdmitted toDomain(RegistryAdmittedEntity registryAdmittedEntity) {
		RegistryAdmitted registryAdmitted = null;
		if(registryAdmittedEntity != null) {
			Vehicle vehicle = VehicleBuilder.toDomain(registryAdmittedEntity.getVehicle());
			registryAdmitted = new RegistryAdmitted(
					vehicle,
					registryAdmittedEntity.getIngreso()
					);
			registryAdmitted.setSelfEntity(registryAdmittedEntity);
		}
		return registryAdmitted;
	}

	public static RegistryAdmittedEntity toEntity(RegistryAdmitted registryAdmitted) {
		if(registryAdmitted.getSelfEntity() != null) return registryAdmitted.getSelfEntity();
		VehicleEntity vehicleEntity = registryAdmitted.getVehicle().getSelfEntity();
		RegistryAdmittedEntity registryAdmittedEntity = new RegistryAdmittedEntity();
		registryAdmittedEntity.setVehicle(vehicleEntity);
		registryAdmittedEntity.setIngreso(registryAdmitted.getIngreso());
		return registryAdmittedEntity;
	}
}
