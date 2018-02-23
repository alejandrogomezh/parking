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
	public List<Register> allRegisters() {
		return RegisterBuilder.toDomain(
					registerRepository.findAll()
				);
	}

	@Override
	public List<Register> vehiclesInParking() {
		return RegisterBuilder.toDomain(
					registerRepository.findBySalidaOrderByIngresoDesc(null)
				);
	}

	@Override
	public List<Register> vehiclesOutParking() {
		return RegisterBuilder.toDomain(
					registerRepository.findBySalidaNotNullOrderByIngresoDesc()
				);
	}

	@Override
	public Register findByVehicleActive(Vehicle vehicle) {
		VehicleEntity vehicleEntity = VehicleBuilder.toEntity(vehicle);
		RegisterEntity registerEntity;
		registerEntity = registerRepository.findByVehicleAndSalida(vehicleEntity, null);
		return RegisterBuilder.toDomain(registerEntity);
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
