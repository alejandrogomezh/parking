package testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import co.ceiba.parking.dominio.objetos.Registro;
import co.ceiba.parking.dominio.objetos.Vehiculo;
import testutilidades.FechaTest;

public class RegistroTestDataBuilder {
	private static final Date INGRESO = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 12, 25);
	private static final Date SALIDA = FechaTest.crearFechaConHora(25, Calendar.MAY, 2018, 10, 10);
	
	private Vehiculo vehiculo;
	private Date ingreso;
	private Date salida;
	
	public RegistroTestDataBuilder() {
		this.vehiculo = new MotoTestDataBuilder().build();
		this.ingreso = INGRESO;
		this.salida = SALIDA;
	}
	
	public RegistroTestDataBuilder conVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public RegistroTestDataBuilder conIngreso(Date ingreso) {
		this.ingreso = ingreso;
		return this;
	}
	
	public RegistroTestDataBuilder conSalida(Date salida) {
		this.salida = salida;
		return this;
	}
	
	public Registro build() {
		return new Registro(vehiculo, ingreso, salida);
	}
	
}
