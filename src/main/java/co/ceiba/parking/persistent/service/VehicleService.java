package co.ceiba.parking.persistent.service;

import co.ceiba.parking.domain.objects.Vehicle;


public interface VehicleService{
	Vehicle findByTipoAndPlaca(String tipo, String placa);
	Vehicle save(Vehicle vehicle);
	//@Query("SELECT v from vehicle v where (v.tipo = :tipo) and (v.placa = :placa)")
	//Vehicle obtenerPorTipoYPlaca(@Param("tipo") String tipo, @Param("placa") String placa);
}
