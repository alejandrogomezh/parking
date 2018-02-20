package testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;
import testutilidades.FechaTest;

public class RegistryAdmittedTestDataBuilder {
	private static final Date INGRESO = FechaTest.crearFecha(24, Calendar.MAY, 2018);
	
	private Vehicle vehicle;
	private Date ingreso;
	
	public RegistryAdmittedTestDataBuilder() {
		this.vehicle = new MotoTestDataBuilder().build();
		this.ingreso = INGRESO;
	}
	
	public RegistryAdmittedTestDataBuilder conVehiculo(Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}
	
	public RegistryAdmittedTestDataBuilder conIngreso(Date ingreso) {
		this.ingreso = ingreso;
		return this;
	}
	
	public RegistryAdmitted build() {
		return new RegistryAdmitted(
			vehicle,
			ingreso
		);
	}
	
}
