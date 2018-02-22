package co.ceiba.parking.persistent.service;

import co.ceiba.parking.persistent.service.InvoiceService;
import co.ceiba.parking.persistent.service.RegisterService;
import co.ceiba.parking.persistent.service.VehicleService;

public interface ServicesPersistent {
	InvoiceService getInvoiceService();
	RegisterService getRegisterService();
	VehicleService getVehicleService();
}
