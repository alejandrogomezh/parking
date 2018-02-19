package co.ceiba.parking.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.dominio.Vigilante;
import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.persistencia.administrador.AdministradorPersistencia;

@RestController
public class ParkingController {
  private final AtomicLong counter = new AtomicLong();
  
  AdministradorPersistencia administradorPersistencia;
  Vigilante vigilante;
  
  public ParkingController() {
  	administradorPersistencia = new AdministradorPersistencia();
  	vigilante = new Vigilante(administradorPersistencia);
  }

  @RequestMapping("/ingresar")
  public String greeting(
  		@RequestParam(value="tipo", defaultValue="") String tipo,
  		@RequestParam(value="placa", defaultValue="") String placa,
  		@RequestParam(value="cilindraje", defaultValue="0") int cilindraje) {
  	
  	Vehiculo vehiculo = new Vehiculo(tipo, placa, cilindraje);
  	return vigilante.ingreso(vehiculo);
  }

}
