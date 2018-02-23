package co.ceiba.parking.persistent.builder;

import java.util.ArrayList;
import java.util.List;

import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.entities.RegisterEntity;
import co.ceiba.parking.persistent.entities.VehicleEntity;

public class RegisterBuilder {

	private RegisterBuilder() {
		
	}
	
	public static Register toDomain(RegisterEntity registerEntity) {
		Register register = null;
		if(registerEntity != null) {
			Vehicle vahiculo = VehicleBuilder.toDomain(registerEntity.getVehicle());
			register = new Register(
					vahiculo,
					registerEntity.getIngreso(),
					registerEntity.getSalida()
					);
			register.setSelfEntity(registerEntity);
		}
		return register;
	}
	
	public static List<Register> toDomain(List<RegisterEntity> registerEntities) {
		List<Register> registers = new ArrayList<>();
		for(RegisterEntity registerEntity : registerEntities) {
			registers.add(toDomain(registerEntity));
		}
		return registers;
	}
	
	public static RegisterEntity toEntity(Register register) {
		RegisterEntity registerEntity = register.getSelfEntity();
		if(register.getSelfEntity() == null) {
			registerEntity = new RegisterEntity();
		}
		VehicleEntity vehicleEntity = register.getVehicle().getSelfEntity();
		registerEntity.setVehicle(vehicleEntity);
		registerEntity.setIngreso(register.getIngreso());
		registerEntity.setSalida(register.getSalida());
		return registerEntity;
	}
	
	public static List<RegisterEntity> toEntity(List<Register> registers) {
		List<RegisterEntity> registerEntities = new ArrayList<>();
		for(Register register : registers) {
			registerEntities.add(toEntity(register));
		}
		return registerEntities;
	}
	
	
}
