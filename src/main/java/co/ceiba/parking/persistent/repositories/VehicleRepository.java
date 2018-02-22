package co.ceiba.parking.persistent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parking.persistent.entities.VehicleEntity;
import java.lang.String;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
	List<VehicleEntity> findByPlaca(String placa);
	@SuppressWarnings("unchecked")
	VehicleEntity save(VehicleEntity vehicleEntity);
}
