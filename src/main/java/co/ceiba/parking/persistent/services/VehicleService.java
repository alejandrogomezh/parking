package co.ceiba.parking.persistent.services;

import co.ceiba.parking.domain.objects.Vehicle;


public interface VehicleService{
	Vehicle findByPlaca(String placa);
	Vehicle save(Vehicle vehicle);
	//@Query("SELECT v from vehicle v where (v.tipo = :tipo) and (v.placa = :placa)")
	//Vehicle obtenerPorTipoYPlaca(@Param("tipo") String tipo, @Param("placa") String placa);
}
