package co.ceiba.parking.persistent.service;

import java.util.List;

import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;

public interface RegisterService{
	Register findByVehicleActive(Vehicle vehicle);
	List<Register> allVehiclesActives();
	int countByTypeVehicle(Vehicle vehicle);
	Register save(Register register);
}
