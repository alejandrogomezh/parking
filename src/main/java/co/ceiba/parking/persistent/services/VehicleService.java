package co.ceiba.parking.persistent.services;

import co.ceiba.parking.domain.objects.Vehicle;


public interface VehicleService{
	Vehicle findByPlaca(String placa);
	Vehicle save(Vehicle vehicle);
}
