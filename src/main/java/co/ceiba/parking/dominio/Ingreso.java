package co.ceiba.parking.dominio;

import java.util.Calendar;
import java.util.Date;

import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.dominio.repositorio.RegistroIngresoRepositorio;
import co.ceiba.parking.dominio.repositorio.RepositorioAdministrador;
import co.ceiba.parking.mensajes.Mensajes;

public class Ingreso {
	private RegistroIngresoRepositorio registroIngresoRepositorio;
	
	private String msg;

	public Ingreso(RepositorioAdministrador repositorioAdministrador) {
		this.registroIngresoRepositorio = repositorioAdministrador.getIngresadosRepositorio();
		msg = "";
	}

	public RegistroIngreso ingresar(Date fecha, Vehiculo vehiculo) {
		msg = "";
		if (puedeIngresar(fecha, vehiculo)) {
			msg = Mensajes.INGRESO;
			return  new RegistroIngreso(vehiculo, fecha).persistente(registroIngresoRepositorio);
		}
		return null;
	}

	private boolean puedeIngresar(Date fecha, Vehiculo vehiculo) {
		RegistroIngreso ingreso = registroIngresoRepositorio.obtenerPorVehiculo(vehiculo);
		if(ingreso != null) {
			msg = Mensajes.YA_HABIA_INGRESADO;
			return false;
		}
		String placa = vehiculo.getPlaca().toLowerCase();
		if (placa.startsWith("a")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);
			int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
			if ((diaSemana == Calendar.SATURDAY) || (diaSemana == Calendar.SUNDAY)) {
				msg = Mensajes.INGRESO_NO_AUTORIZADO;
				return false;
			}
		}
		Condiciones condiciones = Condiciones.get(vehiculo);
		if (registroIngresoRepositorio.obtenerCantidadPorTipoVehiculo(vehiculo) >= condiciones.getCupo()) {
			msg = Mensajes.NO_HAY_CUPO;
			return false;
		}
		return true;
	}
	
	public String getMsg() {
		return msg;
	}

}
