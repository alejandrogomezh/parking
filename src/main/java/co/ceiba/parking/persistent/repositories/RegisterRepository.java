package co.ceiba.parking.persistent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.persistent.entities.RegisterEntity;

public interface RegisterRepository extends JpaRepository<RegisterEntity, Long>{
	RegisterEntity save(RegisterEntity register);
}
