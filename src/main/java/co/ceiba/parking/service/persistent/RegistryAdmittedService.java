package co.ceiba.parking.service.persistent;

import java.util.List;

import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;

public interface RegistryAdmittedService{
	List<RegistryAdmitted> findByVehicle(Vehicle vehicle);	
	int countByTypeVehicle(String tipo);
	RegistryAdmitted save(RegistryAdmitted ingresar);
	
	//@Query("SELECT COUNT(r) from registryadmitted r where r.vehicle.tipo = :vehicle")
	//int obtenerCantidadPorTipoVehiculo(@Param("vehicle") Vehicle vehicle);
	//@Query("SELECT r from registryadmitted r where r.vehicle = :vehicle")
	//RegistryAdmitted obtenerPorVehiculo(@Param("vehicle") Vehicle vehicle);

}
