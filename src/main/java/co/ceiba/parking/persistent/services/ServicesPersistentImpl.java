package co.ceiba.parking.persistent.services;

import co.ceiba.parking.persistent.services.InvoiceService;
import co.ceiba.parking.persistent.services.RegisterService;
import co.ceiba.parking.persistent.services.VehicleService;

public class ServicesPersistentImpl implements ServicesPersistent{

	InvoiceService invoiceService;
	RegisterService registerService;
	VehicleService vehicleService;

	public ServicesPersistentImpl(InvoiceService invoiceService, RegisterService registerService,
			VehicleService vehicleService) {
		this.invoiceService = invoiceService;
		this.registerService = registerService;
		this.vehicleService = vehicleService;
	}

	@Override
	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	@Override
	public RegisterService getRegisterService() {
		return registerService;
	}

	@Override
	public VehicleService getVehicleService() {
		return vehicleService;
	}

}
