package co.ceiba.parking.persistent.service;

import java.util.List;

import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;

public interface RegisterService{
	List<Register> allRegisters();
	List<Register> vehiclesInParking();
	List<Register> vehiclesOutParking();
	
	Register findByVehicleActive(Vehicle vehicle);
	int countByTypeVehicle(Vehicle vehicle);
	Register save(Register register);
}
