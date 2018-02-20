package co.ceiba.parking.service.persistent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.persistent.build.InvoiceBuilder;
import co.ceiba.parking.persistent.entities.InvoiceEntity;
import co.ceiba.parking.persistent.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Override
	public Invoice save(Invoice invoice) {
		InvoiceEntity invoiceEntity = InvoiceBuilder.toEntity(invoice);
		return InvoiceBuilder.toDomain(invoiceRepository.save(invoiceEntity));
	}

}
