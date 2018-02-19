package co.ceiba.parking.dominio;

import java.util.Date;

import co.ceiba.parking.dominio.RegistroIngreso;
import co.ceiba.parking.dominio.objetos.Factura;
import co.ceiba.parking.dominio.objetos.Registro;
import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.dominio.repositorio.FacturaRepositorio;
import co.ceiba.parking.dominio.repositorio.RegistroIngresoRepositorio;
import co.ceiba.parking.dominio.repositorio.RegistroRepositorio;
import co.ceiba.parking.dominio.repositorio.RepositorioAdministrador;
import co.ceiba.parking.mensajes.Mensajes;

public class Salida {
	
	private RegistroIngresoRepositorio registroIngresoRepositorio;
	private RegistroRepositorio registroRepositorio;
	private FacturaRepositorio facturaRepositorio;
	private String msg;
	
	
	public Salida(RepositorioAdministrador repositorioAdministrador) {
		registroIngresoRepositorio = repositorioAdministrador.getIngresadosRepositorio();
		facturaRepositorio = repositorioAdministrador.getFacturaRepositorio();
		registroRepositorio = repositorioAdministrador.getRegistroRepositorio();
		msg = "";
	}
	
	public Factura salir(Date salida, Vehiculo vehiculo) {
		RegistroIngreso ingresado = registroIngresoRepositorio.obtenerPorVehiculo(vehiculo);
		if(ingresado != null) {
			Condiciones condicion = Condiciones.get(vehiculo);
			Registro registro = new Registro(
					vehiculo,
					ingresado.getIngreso(),
					salida
					).persistente(registroRepositorio);
			Tiempos tiempos = new Tiempos(condicion, registro).calcular();
			Costos costos = new Costos(tiempos).calcular();
			
			return new Factura(
					registro,
					tiempos.getDias(),
					tiempos.getHoras(),
					costos.getValorDias(),
					costos.getValorHoras(),
					costos.getValorRecargo(),
					costos.getValorTotal()
					).persistente(facturaRepositorio);
		}else {
			msg = Mensajes.NO_A_INGRESADO;
		}
		return null;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
