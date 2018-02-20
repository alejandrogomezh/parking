package co.ceiba.parking.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.persistencia.entidad.Factura;

public interface FacturaRepositorio extends JpaRepository<Factura, Long>{
	Factura save(Factura factura);
}
