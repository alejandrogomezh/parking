package co.ceiba.parking.persistent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.persistent.builder.InvoiceBuilder;
import co.ceiba.parking.persistent.entities.InvoiceEntity;
import co.ceiba.parking.persistent.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;
	
	public InvoiceServiceImpl() {
		
	}

	public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@Override
	public Invoice save(Invoice invoice) {
		InvoiceEntity invoiceEntity = InvoiceBuilder.toEntity(invoice);
		invoiceEntity = invoiceRepository.save(invoiceEntity);
		return InvoiceBuilder.toDomain(invoiceEntity);
	}

}
