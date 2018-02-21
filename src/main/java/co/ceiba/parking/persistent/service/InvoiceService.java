package co.ceiba.parking.persistent.service;

import co.ceiba.parking.domain.objects.Invoice;

public interface InvoiceService {
	Invoice save(Invoice invoice);
}
