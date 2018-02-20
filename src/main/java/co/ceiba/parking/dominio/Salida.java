package co.ceiba.parking.dominio;

import java.util.Date;

import co.ceiba.parking.dominio.repositorio.FacturaRepositorio;
import co.ceiba.parking.dominio.repositorio.RegistroIngresoRepositorio;
import co.ceiba.parking.dominio.repositorio.RegistroRepositorio;
import co.ceiba.parking.dominio.repositorio.RepositorioAdministrador;
import co.ceiba.parking.mensajes.Mensajes;
import co.ceiba.parking.persistencia.entidad.Factura;
import co.ceiba.parking.persistencia.entidad.Registro;
import co.ceiba.parking.persistencia.entidad.RegistroIngreso;
import co.ceiba.parking.persistencia.entidad.Vehiculo;

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
			Registro registro = new Registro();
			registro.setVehiculo(vehiculo);
			registro.setIngreso(ingresado.getIngreso());
			registro.setSalida(salida);					
			registroRepositorio.save(registro);
			Tiempos tiempos = new Tiempos(condicion, registro).calcular();
			Costos costos = new Costos(tiempos).calcular();
			
			Factura factura = new Factura();
			
			factura.setRegistro(registro);
			factura.setDias(tiempos.getDias());
			factura.setHoras(tiempos.getHoras());
	
			factura.setValorDias(costos.getValorDias());
			factura.setValorHoras(costos.getValorHoras());
			factura.setValorRecargo(costos.getValorRecargo());
			factura.setValorTotal(costos.getValorTotal());					
			facturaRepositorio.save(factura);
			return factura;
			
		}else {
			msg = Mensajes.NO_A_INGRESADO;
		}
		return null;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
