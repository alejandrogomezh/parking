package co.ceiba.parking.persistent.repositories;

import co.ceiba.parking.domain.objects.Vehicle;


public interface VehicleRepository{
	Vehicle findByTipoPlaca(String tipo, String placa);
	Vehicle save(Vehicle vehicleEntity);
	//@Query("SELECT v from vehicle v where (v.tipo = :tipo) and (v.placa = :placa)")
	//Vehicle obtenerPorTipoYPlaca(@Param("tipo") String tipo, @Param("placa") String placa);
}
