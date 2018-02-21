package co.ceiba.parking.domain;

import java.util.Date;

import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.service.InvoiceService;
import co.ceiba.parking.persistent.service.RegisterService;
import co.ceiba.parking.persistent.service.RegistryAdmittedService;
import co.ceiba.parking.persistent.service.ServicesPersistent;

public class OutVehicle {
	
	private RegistryAdmittedService registryAdmittedService;
	private RegisterService registerService;
	private InvoiceService invoiceService;
	private String msg;
	
	
	public OutVehicle(ServicesPersistent servicesPersistent) {
		registryAdmittedService = servicesPersistent.getRegistryAdmittedService();
		invoiceService = servicesPersistent.getInvoiceService();
		registerService = servicesPersistent.getRegisterService();
		msg = "";
	}
	
	public Invoice out(Date salida, Vehicle vehicle) {
		RegistryAdmitted admitted = registryAdmittedService.findByVehicle(vehicle);
		if(admitted == null) {
			msg = Messages.NO_A_INGRESADO;
			return null;
		}
		Register register = new Register(
				vehicle,
				admitted.getIngreso(),
				salida
				).persist(registerService);				
		
		Time time = new Time(Conditions.get(vehicle), register).calculate();
		Cost cost = new Cost(time).calculate();
		
		return new Invoice(
				register,
				time.getDias(),
				time.getHoras(),
				cost.getValorDias(),
				cost.getValorHoras(),
				cost.getValorRecargo(),
				cost.getValorTotal()	
				).persist(invoiceService);
	}
	
	public String getMsg() {
		return msg;
	}
	
}
