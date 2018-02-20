package co.ceiba.parking.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.ceiba.parking.persistencia.entidad.RegistroIngreso;
import co.ceiba.parking.persistencia.entidad.Vehiculo;

public interface RegistroIngresoRepositorio extends JpaRepository<RegistroIngreso, Long>{
	@Query("SELECT COUNT(r) from registroingreso  r where r.vehiculo.tipo = :vehiculo.tipo")
	long obtenerCantidadPorTipoVehiculo(@Param("vehiculo") Vehiculo vehiculo);
	@Query("SELECT r from registroingreso r where r.vehiculo.placa = :vehiculo.placa")
	RegistroIngreso obtenerPorVehiculo(@Param("vehiculo") Vehiculo vehiculo);
	RegistroIngreso save(RegistroIngreso ingresar);
}
