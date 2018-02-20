package co.ceiba.parking.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.ceiba.parking.persistencia.entidad.Vehiculo;


public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Long>{
	@Query("SELECT v from vehiculo v where (v.tipo = :tipo) and (v.placa = :placa)")
	Vehiculo obtenerPorTipoYPlaca(@Param("tipo") String tipo, @Param("placa") String placa);
	Vehiculo agregar(Vehiculo vehiculo);
	
}
