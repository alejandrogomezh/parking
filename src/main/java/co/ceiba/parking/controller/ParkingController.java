package co.ceiba.parking.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.dominio.Vigilante;
import co.ceiba.parking.dominio.repositorio.VehiculoRepositorio;
import co.ceiba.parking.persistencia.administrador.AdministradorPersistencia;
import co.ceiba.parking.persistencia.entidad.Vehiculo;

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
  	
  	VehiculoRepositorio vehiculoRepositorio = administradorPersistencia.getVehiculoRepositorio();
  	Vehiculo vehiculo = vehiculoRepositorio.obtenerPorTipoYPlaca(tipo, placa);
  	if(vehiculo == null) {
  		vehiculo = new Vehiculo();
  		vehiculo.setTipo(tipo);
  		vehiculo.setTipo(placa);
  		
  	}
  	return vigilante.ingreso(vehiculo);
  }

}
