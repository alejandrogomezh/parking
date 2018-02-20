package testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;
import testutilidades.FechaTest;

public class RegisterTestDataBuilder {
	private static final Date INGRESO = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 12, 25);
	private static final Date SALIDA = FechaTest.crearFechaConHora(25, Calendar.MAY, 2018, 10, 10);
	
	private Vehicle vehicle;
	private Date ingreso;
	private Date salida;
	
	public RegisterTestDataBuilder() {
		this.vehicle = new MotoTestDataBuilder().build();
		this.ingreso = INGRESO;
		this.salida = SALIDA;
	}
	
	public RegisterTestDataBuilder conVehiculo(Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}
	
	public RegisterTestDataBuilder conIngreso(Date ingreso) {
		this.ingreso = ingreso;
		return this;
	}
	
	public RegisterTestDataBuilder conSalida(Date salida) {
		this.salida = salida;
		return this;
	}
	
	public Register build() {
		return new Register(
			vehicle,
			ingreso,
			salida
		);
	}
	
}
