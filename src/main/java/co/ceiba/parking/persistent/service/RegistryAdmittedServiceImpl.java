package co.ceiba.parking.persistent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.builder.RegistryAdmittedBuilder;
import co.ceiba.parking.persistent.builder.VehicleBuilder;
import co.ceiba.parking.persistent.entities.RegistryAdmittedEntity;
import co.ceiba.parking.persistent.entities.VehicleEntity;
import co.ceiba.parking.persistent.repositories.RegistryAdmittedRepository;

@Service
public class RegistryAdmittedServiceImpl implements RegistryAdmittedService {
	
	@Autowired
	RegistryAdmittedRepository registryAdmittedRepository;
	
	public RegistryAdmittedServiceImpl() {
		
	}
	
	public RegistryAdmittedServiceImpl(RegistryAdmittedRepository registryAdmittedRepository) {
		this.registryAdmittedRepository = registryAdmittedRepository;
	}
	
	@Override
	public RegistryAdmitted findByVehicle(Vehicle vehicle) {
		VehicleEntity vehicleEntity = VehicleBuilder.toEntity(vehicle);
		List<RegistryAdmittedEntity> registryAdmittedEntities;
		registryAdmittedEntities = registryAdmittedRepository.findByVehicle(vehicleEntity);
		if(registryAdmittedEntities.isEmpty()) return null;
		return RegistryAdmittedBuilder.toDomain(registryAdmittedEntities.get(0));
	}

	@Override
	public int countByTypeVehicle(Vehicle vehicle) {
		return registryAdmittedRepository.countByVehicleTipo(vehicle.getTipo());
	}

	@Override
	public RegistryAdmitted save(RegistryAdmitted registryAdmitted) {
		RegistryAdmittedEntity registryAdmittedEntity = RegistryAdmittedBuilder.toEntity(registryAdmitted);
		registryAdmittedEntity = registryAdmittedRepository.save(registryAdmittedEntity);
		return RegistryAdmittedBuilder.toDomain(registryAdmittedEntity);
	}

}
