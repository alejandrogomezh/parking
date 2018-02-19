package testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import co.ceiba.parking.dominio.RegistroIngreso;
import co.ceiba.parking.dominio.objetos.Vehiculo;
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
		return new RegistroIngreso(vehiculo, ingreso);
	}
	
}
