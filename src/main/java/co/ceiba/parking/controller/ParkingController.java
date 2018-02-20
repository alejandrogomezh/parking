package co.ceiba.parking.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.domain.Vigilant;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.service.persistent.ServicesPersistent;
import co.ceiba.parking.service.persistent.ServicesPersistentImpl;
import co.ceiba.parking.service.persistent.VehicleService;

@RestController
public class ParkingController {
  private final AtomicLong counter = new AtomicLong();
  
  ServicesPersistent servicesPersistent;
  Vigilant vigilant;
  
  public ParkingController() {
  	servicesPersistent = new ServicesPersistentImpl();
  	vigilant = new Vigilant(servicesPersistent);
  }

  @RequestMapping("/ingresar")
  public String greeting(
  		@RequestParam(value="tipo", defaultValue="") String tipo,
  		@RequestParam(value="placa", defaultValue="") String placa,
  		@RequestParam(value="cilindraje", defaultValue="0") int cilindraje) {
  	
  	VehicleService vehicleService = servicesPersistent.getVehicleService();
  	Vehicle vehicle = vehicleService.findByTipoPlaca(tipo, placa);
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
