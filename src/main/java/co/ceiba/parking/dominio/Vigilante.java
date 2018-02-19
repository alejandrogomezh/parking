package co.ceiba.parking.dominio;

import java.util.Date;

import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.dominio.repositorio.RepositorioAdministrador;

public class Vigilante {
	private Ingreso ingreso;
	private Salida salida;
	
	public Vigilante(RepositorioAdministrador repositorioAdministrador) {
		ingreso = new Ingreso(repositorioAdministrador);
		salida = new Salida(repositorioAdministrador);
	}
	
	public String ingreso(Vehiculo vehiculo) {
		ingreso.ingresar(fechaActual(), vehiculo);
		return ingreso.getMsg();
	}
	
	public String salida(Vehiculo vehiculo) {
		salida.salir(fechaActual(), vehiculo);
		return salida.getMsg();
	}
	
	protected Date fechaActual() {
		return new Date();
	}
}
