package co.ceiba.parking.persistent.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parking.persistent.entities.RegisterEntity;
import co.ceiba.parking.persistent.entities.VehicleEntity;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long>{
	List<RegisterEntity> findByVehicleAndSalida(VehicleEntity vehicleEntity, Date salida);
	List<RegisterEntity> findBySalidaOrderByVehicleTipo(Date salida);
	int countByVehicleTipoAndSalida(String tipo, Date salida);	@SuppressWarnings("unchecked")
	RegisterEntity save(RegisterEntity register);
}
