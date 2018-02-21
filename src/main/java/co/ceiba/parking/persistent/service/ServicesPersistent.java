package co.ceiba.parking.persistent.service;

import co.ceiba.parking.persistent.service.InvoiceService;
import co.ceiba.parking.persistent.service.RegisterService;
import co.ceiba.parking.persistent.service.RegistryAdmittedService;
import co.ceiba.parking.persistent.service.VehicleService;

public interface ServicesPersistent {
	InvoiceService getInvoiceService();
	RegistryAdmittedService getRegistryAdmittedService();
	RegisterService getRegisterService();
	VehicleService getVehicleService();
}
