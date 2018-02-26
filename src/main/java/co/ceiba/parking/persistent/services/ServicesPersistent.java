package co.ceiba.parking.persistent.services;

import co.ceiba.parking.persistent.services.InvoiceService;
import co.ceiba.parking.persistent.services.RegisterService;
import co.ceiba.parking.persistent.services.VehicleService;

public interface ServicesPersistent {
	InvoiceService getInvoiceService();
	RegisterService getRegisterService();
	VehicleService getVehicleService();
}
