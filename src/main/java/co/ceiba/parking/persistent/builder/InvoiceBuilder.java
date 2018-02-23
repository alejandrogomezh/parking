package co.ceiba.parking.persistent.builder;

import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.persistent.entities.InvoiceEntity;
import co.ceiba.parking.persistent.entities.RegisterEntity;

public class InvoiceBuilder {

	private InvoiceBuilder() {
		
	}
	
	public static Invoice toDomain(InvoiceEntity invoiceEntity) {
		Invoice invoice = null;
		if(invoiceEntity != null) {
			Register register = RegisterBuilder.toDomain(invoiceEntity.getRegister());
			invoice = new Invoice(
					register,
					invoiceEntity.getDias(),
					invoiceEntity.getHoras(),
					invoiceEntity.getValorDias(),
					invoiceEntity.getValorHoras(),
					invoiceEntity.getValorRecargo(),
					invoiceEntity.getValorTotal()
					);
			invoice.setSelfEntity(invoiceEntity);
		}
		return invoice;
	}
	
	public static InvoiceEntity toEntity(Invoice invoice) {
		InvoiceEntity invoiceEntity = invoice.getSelfEntity();
		RegisterEntity register = invoice.getRegister().getSelfEntity();
		if(invoice.getSelfEntity() == null) {
			invoiceEntity = new InvoiceEntity();
		}
		invoiceEntity.setRegister(register);
		invoiceEntity.setDias(invoice.getDias());
		invoiceEntity.setHoras(invoice.getHoras());
		invoiceEntity.setValorDias(invoice.getValorDias());
		invoiceEntity.setValorHoras(invoice.getValorHoras());
		invoiceEntity.setValorRecargo(invoice.getValorRecargo());
		invoiceEntity.setValorTotal(invoice.getValorTotal());

		return invoiceEntity;
	}
	
}
