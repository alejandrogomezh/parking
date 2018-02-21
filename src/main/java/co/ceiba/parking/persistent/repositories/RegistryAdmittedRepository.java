package co.ceiba.parking.persistent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parking.persistent.entities.RegisterEntity;
import co.ceiba.parking.persistent.entities.RegistryAdmittedEntity;
import co.ceiba.parking.persistent.entities.VehicleEntity;

@Repository
public interface RegistryAdmittedRepository extends JpaRepository<RegisterEntity, Long>{
	List<RegistryAdmittedEntity> findByVehicle(VehicleEntity vehicle);	
	int countByVehicleTipo(String tipo);
	RegistryAdmittedEntity save(RegistryAdmittedEntity ingresar);
	
	//@Query("SELECT COUNT(r) from registryadmitted r where r.vehicle.tipo = :vehicle")
	//int obtenerCantidadPorTipoVehiculo(@Param("vehicle") Vehicle vehicle);
	//@Query("SELECT r from registryadmitted r where r.vehicle = :vehicle")
	//RegistryAdmitted obtenerPorVehiculo(@Param("vehicle") Vehicle vehicle);

}
