package co.ceiba.parking.domain;

import java.util.Date;
import java.util.List;

import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.service.persistent.InvoiceService;
import co.ceiba.parking.service.persistent.RegisterService;
import co.ceiba.parking.service.persistent.RegistryAdmittedService;
import co.ceiba.parking.service.persistent.ServicesPersistent;

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
		List<RegistryAdmitted> admittedList = registryAdmittedService.findByVehicle(vehicle);
		if(admittedList.isEmpty()) {
			msg = Messages.NO_A_INGRESADO;
			return null;
		}
		RegistryAdmitted admitted = admittedList.get(0);
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
