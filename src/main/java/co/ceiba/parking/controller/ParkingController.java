package co.ceiba.parking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.domain.Vigilant;
import co.ceiba.parking.domain.excepcion.ParkingException;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.builder.RegisterBuilder;
import co.ceiba.parking.persistent.entities.RegisterEntity;
import co.ceiba.parking.persistent.entities.VehicleEntity;
import co.ceiba.parking.persistent.repositories.InvoiceRepository;
import co.ceiba.parking.persistent.repositories.RegisterRepository;
import co.ceiba.parking.persistent.repositories.VehicleRepository;
import co.ceiba.parking.persistent.service.InvoiceServiceImpl;
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
  public Messages login(@RequestBody VehicleEntity vehicleEntity) {
  	String msg = "";
  	return new Messages(msg);
  }

  @RequestMapping(value = "/enter", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Messages enter(@RequestBody VehicleEntity vehicleEntity) {
  	Vigilant vigilant = new Vigilant(servicesPersistent);
  	String msg;
  	try {
  		msg = vigilant.ingreso(vehicleEntity.getTipo(), vehicleEntity.getPlaca(), vehicleEntity.getCilindraje());
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
  	return new Messages(msg);
  }
  
//  @RequestMapping(value = "/admitted", method = RequestMethod.GET)
//  @ResponseBody
//  public List<Register> admitted() {
//  	List<Register> registers = new ArrayList<Register>();
//   	registers.add(new Register(new Vehicle("tipo1", "placa1", 125) , new Date()));
//   	registers.add(new Register(new Vehicle("tipo2", "placa2", 125) , new Date()));
//  	return registers;//("tipo", "placa", 125);
//  }

  @RequestMapping(value = "/admitted", method = RequestMethod.GET)
  @ResponseBody
  public List<Register> admitted() {
  	Vigilant vigilant = new Vigilant(servicesPersistent);
  	List<Register> registers = vigilant.getAdmittedList();
  	return registers;
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
