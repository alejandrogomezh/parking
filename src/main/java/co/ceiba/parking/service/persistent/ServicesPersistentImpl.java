package co.ceiba.parking.service.persistent;

import co.ceiba.parking.service.persistent.InvoiceService;
import co.ceiba.parking.service.persistent.RegisterService;
import co.ceiba.parking.service.persistent.RegistryAdmittedService;
import co.ceiba.parking.service.persistent.VehicleService;

public class ServicesPersistentImpl implements ServicesPersistent{

	InvoiceService invoiceService;
	RegistryAdmittedService registryAdmittedService;
	RegisterService registerService;
	VehicleService vehicleService;

	@Override
	public InvoiceService getInvoiceService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistryAdmittedService getRegistryAdmittedService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterService getRegisterService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleService getVehicleService() {
		// TODO Auto-generated method stub
		return null;
	}

}
