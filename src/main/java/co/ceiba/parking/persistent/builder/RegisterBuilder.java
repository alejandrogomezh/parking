package co.ceiba.parking.persistent.builder;

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
	
	public static RegisterEntity toEntity(Register register) {
		if(register.getSelfEntity() != null) return register.getSelfEntity();
		VehicleEntity vehicleEntity = register.getVehicle().getSelfEntity();
		RegisterEntity registerEntity = new RegisterEntity();
		registerEntity.setVehicle(vehicleEntity);
		registerEntity.setIngreso(register.getIngreso());
		registerEntity.setSalida(register.getSalida());
		return registerEntity;
	}
	
}
