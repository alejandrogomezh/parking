package co.ceiba.parking.persistent.service;

import co.ceiba.parking.persistent.service.InvoiceService;
import co.ceiba.parking.persistent.service.RegisterService;
import co.ceiba.parking.persistent.service.VehicleService;

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
