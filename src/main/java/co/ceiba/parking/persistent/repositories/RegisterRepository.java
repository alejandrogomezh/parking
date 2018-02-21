package co.ceiba.parking.persistent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parking.persistent.entities.RegisterEntity;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long>{
	@SuppressWarnings("unchecked")
	RegisterEntity save(RegisterEntity register);
}
