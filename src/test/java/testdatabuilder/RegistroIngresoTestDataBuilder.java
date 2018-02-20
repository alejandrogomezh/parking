package testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import co.ceiba.parking.persistencia.entidad.RegistroIngreso;
import co.ceiba.parking.persistencia.entidad.Vehiculo;
import testutilidades.FechaTest;

public class RegistroIngresoTestDataBuilder {
	private static final Date INGRESO = FechaTest.crearFecha(24, Calendar.MAY, 2018);
	
	private Vehiculo vehiculo;
	private Date ingreso;
	
	public RegistroIngresoTestDataBuilder() {
		this.vehiculo = new MotoTestDataBuilder().build();
		this.ingreso = INGRESO;
	}
	
	public RegistroIngresoTestDataBuilder conVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public RegistroIngresoTestDataBuilder conIngreso(Date ingreso) {
		this.ingreso = ingreso;
		return this;
	}
	
	public RegistroIngreso build() {
		RegistroIngreso registroIngreso = new RegistroIngreso();
		registroIngreso.setVehiculo(vehiculo);
		registroIngreso.setIngreso(ingreso);
		return registroIngreso;
	}
	
}
