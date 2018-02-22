package co.ceiba.parking.persistent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.builder.RegisterBuilder;
import co.ceiba.parking.persistent.builder.VehicleBuilder;
import co.ceiba.parking.persistent.entities.RegisterEntity;
import co.ceiba.parking.persistent.entities.VehicleEntity;
import co.ceiba.parking.persistent.repositories.RegisterRepository;

@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	RegisterRepository registerRepository;

	public RegisterServiceImpl() {
		
	}
	
	public RegisterServiceImpl(RegisterRepository registerRepository) {
		this.registerRepository = registerRepository;
	}
	
	@Override
	public Register findByVehicleActive(Vehicle vehicle) {
		VehicleEntity vehicleEntity = VehicleBuilder.toEntity(vehicle);
		List<RegisterEntity> registerEntities;
		registerEntities = registerRepository.findByVehicleAndSalida(vehicleEntity, null);
		if(registerEntities.isEmpty()) return null;
		return RegisterBuilder.toDomain(registerEntities.get(0));
	}

	@Override
	public List<Register> allVehiclesActives() {
		List<RegisterEntity> registerEntities;
		registerEntities = registerRepository.findBySalidaOrderByVehicleTipo(null);
		return RegisterBuilder.toDomain(registerEntities);
	}

	@Override
	public int countByTypeVehicle(Vehicle vehicle) {
		return registerRepository.countByVehicleTipoAndSalida(vehicle.getTipo(), null);
	}

	@Override
	public Register save(Register register) {
		RegisterEntity registerEntity = RegisterBuilder.toEntity(register);
		registerEntity = registerRepository.save(registerEntity);
		return RegisterBuilder.toDomain(registerEntity);
	}

}
