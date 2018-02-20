package co.ceiba.parking.service.persistent;

import co.ceiba.parking.domain.objects.Invoice;

public interface InvoiceService {
	Invoice save(Invoice invoice);
}
