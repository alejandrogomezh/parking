package co.ceiba.parking.persistent.service;

import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;

public interface RegistryAdmittedService{
	RegistryAdmitted findByVehicle(Vehicle vehicle);	
	int countByTypeVehicle(Vehicle vehicle);
	RegistryAdmitted save(RegistryAdmitted registryAdmitted);
	
	//@Query("SELECT COUNT(r) from registryadmitted r where r.vehicle.tipo = :vehicle")
	//int obtenerCantidadPorTipoVehiculo(@Param("vehicle") Vehicle vehicle);
	//@Query("SELECT r from registryadmitted r where r.vehicle = :vehicle")
	//RegistryAdmitted obtenerPorVehiculo(@Param("vehicle") Vehicle vehicle);

}
