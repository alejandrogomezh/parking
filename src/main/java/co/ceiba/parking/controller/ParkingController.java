package co.ceiba.parking.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
//import java.util.concurrent.atomic.AtomicLong;
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
import co.ceiba.parking.persistent.service.InvoiceServiceImpl;
import co.ceiba.parking.persistent.service.RegisterService;
import co.ceiba.parking.persistent.service.RegisterServiceImpl;
import co.ceiba.parking.persistent.service.ServicesPersistent;
import co.ceiba.parking.persistent.service.ServicesPersistentImpl;
import co.ceiba.parking.persistent.service.VehicleServiceImpl;

@RestController
public class ParkingController {
  //private final AtomicLong counter = new AtomicLong();
	
	private ServicesPersistent servicesPersistent;
  
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
  
  @RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Messages login(@RequestBody Vehicle vehicle) {
  	String msg = "";
  	return new Messages(msg);
  }

  @RequestMapping(value = "/input", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Messages input(@RequestBody Vehicle vehicle) {
  	Vigilant vigilant = new Vigilant(servicesPersistent);
  	String msg;
  	try {
  		msg = vigilant.ingreso(vehicle);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
  	return new Messages(msg);
  }
  
  @RequestMapping(value = "/output", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object output(@RequestBody Vehicle vehicle) {
  	Vigilant vigilant = new Vigilant(servicesPersistent);
  	String msg;
  	try {
  		return vigilant.salida(vehicle);
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
		}
  	return new ArrayList<Register>();
  }
  
//  @RequestMapping(value = "/admitted", method = RequestMethod.GET)
//  @ResponseBody
//  public List<Vehicle> admitted() {
//  	List<Vehicle> vehicles = new ArrayList<Vehicle>();
//  	vehicles.add(new Vehicle("tipo", "placa", 125));
//  	return vehicles;//("tipo", "placa", 125);
//  }

//  @RequestMapping(value = "/admitted", method = RequestMethod.GET)
//  @ResponseBody
//  public List<RegisterEntity> admitted() {
//  	MappingJackson2HttpMessageConverter converter= new MappingJackson2HttpMessageConverter();
//  	Vigilant vigilant = new Vigilant(servicesPersistent);
//  	return RegisterBuilder.toEntity(vigilant.getAdmittedList());
//  }
//  
//  @RequestMapping(value = "/admitted", method = RequestMethod.GET)
//  @ResponseBody
//  public List<RegisterEntity> admitted() {
//  	Vigilant vigilant = new Vigilant(servicesPersistent);
//  	return RegisterBuilder.toEntity(vigilant.getAdmittedList());
//  }


}
