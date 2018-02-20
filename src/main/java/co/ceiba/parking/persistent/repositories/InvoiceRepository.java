package co.ceiba.parking.persistent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.persistent.entities.InvoiceEntity;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
	InvoiceEntity save(InvoiceEntity invoice);
}
