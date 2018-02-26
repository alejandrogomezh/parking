package co.ceiba.parking.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.domain.Vigilant;
import co.ceiba.parking.domain.excepcion.ParkingException;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.repositories.InvoiceRepository;
import co.ceiba.parking.persistent.repositories.RegisterRepository;
import co.ceiba.parking.persistent.repositories.VehicleRepository;
import co.ceiba.parking.persistent.services.InvoiceServiceImpl;
import co.ceiba.parking.persistent.services.RegisterService;
import co.ceiba.parking.persistent.services.RegisterServiceImpl;
import co.ceiba.parking.persistent.services.ServicesPersistent;
import co.ceiba.parking.persistent.services.ServicesPersistentImpl;
import co.ceiba.parking.persistent.services.VehicleServiceImpl;
import co.ceiba.parking.service.impl.ParkingServicesInjection;

@RestController
public class ParkingController {
	
	private ServicesPersistent servicesPersistent;
  
	@Autowired
	private ParkingServicesInjection parkingServicesInjection;
  @Autowired
  private  InvoiceRepository invoiceRepository;
  @Autowired
  private  RegisterRepository registerRepository;
  @Autowired
  private  VehicleRepository vehicleRepository;

  @PostConstruct
  public void init(){
		servicesPersistent = new ServicesPersistentImpl(
			new InvoiceServiceImpl(invoiceRepository),
			new RegisterServiceImpl(registerRepository),
			new VehicleServiceImpl(vehicleRepository)
			);
  }

  @RequestMapping(value = "/input", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Messages input(@RequestBody Vehicle vehicle) {
  	Vigilant vigilant = getVigilant();
  	String msg;
  	try {
  		msg = vigilant.input(vehicle);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
  	return new Messages(msg);
  }
  
  @RequestMapping(value = "/output", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object output(@RequestBody Vehicle vehicle) {
  	Vigilant vigilant = getVigilant();
  	String msg;
  	try {
  		return vigilant.output(vehicle);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
  	return new Messages(msg);
  }
  
  @RequestMapping(value = "/registerFilter", method = RequestMethod.GET)
  @ResponseBody
  public List<Register> registerFilter(@RequestParam(name = "filter", defaultValue = "") String filter) {
  	RegisterService registerService = servicesPersistent.getRegisterService();
  	switch (filter) {
		case "All":
			return registerService.allRegisters();
		case "In":
			return registerService.vehiclesInParking();
		case "Out":
			return registerService.vehiclesOutParking();
		default:
			return new ArrayList<>();
		}
  }

  protected Vigilant getVigilant() {
  	return new Vigilant(parkingServicesInjection, servicesPersistent);
  }
  
}
