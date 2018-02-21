package co.ceiba.parking.persistent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.parking.persistent.entities.InvoiceEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
	@SuppressWarnings("unchecked")
	InvoiceEntity save(InvoiceEntity invoice);
}
