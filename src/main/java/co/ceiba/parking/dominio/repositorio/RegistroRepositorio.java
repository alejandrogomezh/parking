package co.ceiba.parking.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.persistencia.entidad.Registro;

public interface RegistroRepositorio extends JpaRepository<Registro, Long>{
	Registro save(Registro registro);
}
