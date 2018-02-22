package co.ceiba.parking.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.domain.Vigilant;
import co.ceiba.parking.persistent.repositories.InvoiceRepository;
import co.ceiba.parking.persistent.repositories.RegisterRepository;
import co.ceiba.parking.persistent.repositories.RegistryAdmittedRepository;
import co.ceiba.parking.persistent.repositories.VehicleRepository;
import co.ceiba.parking.persistent.service.InvoiceServiceImpl;
import co.ceiba.parking.persistent.service.RegisterServiceImpl;
import co.ceiba.parking.persistent.service.RegistryAdmittedServiceImpl;
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
  private  RegistryAdmittedRepository registryAdmittedRepository;
  @Autowired
  private  VehicleRepository vehicleRepository;

  @PostConstruct
  public void init(){
		servicesPersistent = new ServicesPersistentImpl(
			new InvoiceServiceImpl(invoiceRepository),
			new RegisterServiceImpl(registerRepository),
			new RegistryAdmittedServiceImpl(registryAdmittedRepository),
			new VehicleServiceImpl(vehicleRepository)
			);
  }
	
  @RequestMapping("/ingresar")
  //@RequestMapping(value = "/ingresar", method = RequestMethod.POST)
  public String greeting(
  		@RequestParam(value="tipo", defaultValue="") String tipo,
  		@RequestParam(value="placa", defaultValue="") String placa,
  		@RequestParam(value="cilindraje", defaultValue="0") int cilindraje) {
  	  	
  	Vigilant vigilant = new Vigilant(servicesPersistent);

  	return vigilant.ingreso(tipo, placa, cilindraje);
  }

}
