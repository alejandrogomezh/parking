package co.ceiba.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.domain.Vigilant;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.repositories.InvoiceRepository;
import co.ceiba.parking.persistent.repositories.RegisterRepository;
import co.ceiba.parking.persistent.repositories.RegistryAdmittedRepository;
import co.ceiba.parking.persistent.repositories.VehicleRepository;
import co.ceiba.parking.persistent.service.InvoiceServiceImpl;
import co.ceiba.parking.persistent.service.RegisterServiceImpl;
import co.ceiba.parking.persistent.service.RegistryAdmittedServiceImpl;
import co.ceiba.parking.persistent.service.ServicesPersistent;
import co.ceiba.parking.persistent.service.ServicesPersistentImpl;
import co.ceiba.parking.persistent.service.VehicleService;
import co.ceiba.parking.persistent.service.VehicleServiceImpl;
import co.ceiba.parking.security.repository.UserRepository;

@RestController
public class ParkingController {
  //private final AtomicLong counter = new AtomicLong();
  
  @Autowired
  private  InvoiceRepository invoiceRepository;
  @Autowired
  private  RegisterRepository registerRepository;
  @Autowired
  private  RegistryAdmittedRepository registryAdmittedRepository;
  @Autowired
  private  VehicleRepository vehicleRepository;

  @RequestMapping("/ingresar")
  public String greeting(
  		@RequestParam(value="tipo", defaultValue="") String tipo,
  		@RequestParam(value="placa", defaultValue="") String placa,
  		@RequestParam(value="cilindraje", defaultValue="0") int cilindraje) {
  	
  	ServicesPersistent servicesPersistent = new ServicesPersistentImpl(
			new InvoiceServiceImpl(invoiceRepository),
			new RegisterServiceImpl(registerRepository),
			new RegistryAdmittedServiceImpl(registryAdmittedRepository),
			new VehicleServiceImpl(vehicleRepository)
			);
  	
  	Vigilant vigilant = new Vigilant(servicesPersistent);

  	VehicleService vehicleService = servicesPersistent.getVehicleService();
  	Vehicle vehicle = vehicleService.findByTipoAndPlaca(tipo, placa);
  	if(vehicle == null) {
  		vehicle = new Vehicle(
  				tipo,
  				placa,
  				cilindraje
  				).persist(vehicleService);
  	}
  	return vigilant.ingreso(vehicle);
  }

}
