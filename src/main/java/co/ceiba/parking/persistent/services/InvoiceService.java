package co.ceiba.parking.persistent.services;

import co.ceiba.parking.domain.objects.Invoice;

public interface InvoiceService {
	Invoice save(Invoice invoice);
}
