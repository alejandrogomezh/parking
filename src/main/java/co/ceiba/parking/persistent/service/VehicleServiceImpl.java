package co.ceiba.parking.persistent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.builder.VehicleBuilder;
import co.ceiba.parking.persistent.entities.VehicleEntity;
import co.ceiba.parking.persistent.repositories.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;

	public VehicleServiceImpl() {
		
	}
	
	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	@Transactional
	public Vehicle findByPlaca(String placa) {
		List<VehicleEntity> vehicleEntities;
		vehicleEntities = vehicleRepository.findByPlaca(placa);
		if(vehicleEntities.isEmpty()) return null;
		return VehicleBuilder.toDomain(vehicleEntities.get(0));
	}
	
	@Transactional
	public Vehicle save(Vehicle vehicle) {
		VehicleEntity vehicleEntity = VehicleBuilder.toEntity(vehicle);
		vehicleEntity = vehicleRepository.save(vehicleEntity);
		return VehicleBuilder.toDomain(vehicleEntity);
	}
}
