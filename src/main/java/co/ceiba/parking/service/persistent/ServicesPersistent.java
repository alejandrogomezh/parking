package co.ceiba.parking.service.persistent;

import co.ceiba.parking.service.persistent.InvoiceService;
import co.ceiba.parking.service.persistent.RegisterService;
import co.ceiba.parking.service.persistent.RegistryAdmittedService;
import co.ceiba.parking.service.persistent.VehicleService;

public interface ServicesPersistent {
	InvoiceService getInvoiceService();
	RegistryAdmittedService getRegistryAdmittedService();
	RegisterService getRegisterService();
	VehicleService getVehicleService();
}
