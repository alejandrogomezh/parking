package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import co.ceiba.parking.domain.Conditions;
import co.ceiba.parking.domain.Vigilant;
import co.ceiba.parking.domain.excepcion.ParkingException;
import co.ceiba.parking.domain.objects.Carro;
import co.ceiba.parking.domain.objects.Moto;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.services.RegisterService;
import co.ceiba.parking.persistent.services.ServicesPersistent;
import co.ceiba.parking.persistent.services.VehicleService;

import co.ceiba.parking.services.ParkingService;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;
import testutilidades.FechaTest;

public class IngresoTest {

	private ParkingService parkingService;
	
	private ServicesPersistent servicesPersistent;
	private RegisterService registerService;
	private VehicleService vehicleService;
	
	
	@Before
	public void setup() {
		parkingService = mock(ParkingService.class);
		servicesPersistent = mock(ServicesPersistent.class);
		registerService = mock(RegisterService.class);
		vehicleService = mock(VehicleService.class);
	}
	
	@Test public void noIngresarPlacaADiaDomingo() {
		// arrange
		Date ingreso = FechaTest.crearFecha(18, Calendar.FEBRUARY, 2018); // Domingo 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);
		
		when(parkingService.dateNow()).thenReturn(ingreso);
		Vigilant vigilant = new Vigilant(parkingService, servicesPersistent);
		
		String msg;
		// act
		try {
			msg = vigilant.input(moto);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.INGRESO_NO_AUTORIZADO, msg);
	}

	@Test public void noIngresarPlacaADiaSabado() {
		// arrange
		Date ingreso = FechaTest.crearFecha(17, Calendar.FEBRUARY, 2018); // Sabado 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);
		
		when(parkingService.dateNow()).thenReturn(ingreso);
		Vigilant vigilant = new Vigilant(parkingService, servicesPersistent);

		String msg;
		// act
		try {
			msg = vigilant.input(moto);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.INGRESO_NO_AUTORIZADO, msg);
	}

	@Test public void ingresarPlacaNoADiaDomingo() {
		// arrange
		Date ingreso = FechaTest.crearFecha(18, Calendar.FEBRUARY, 2018); // Domingo 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("CBC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);

		when(parkingService.dateNow()).thenReturn(ingreso);
		Vigilant vigilant = new Vigilant(parkingService, servicesPersistent);

		String msg;
		// act
		try {
			msg = vigilant.input(moto);
			//fail();
		} catch (ParkingException e) {
			
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.INGRESO_SATISFACTORIO, msg);
	}

	@Test public void ingresarPlacaADiaLunes() {
		// arrange
		Date ingreso = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);

		when(parkingService.dateNow()).thenReturn(ingreso);
		Vigilant vigilant = new Vigilant(parkingService, servicesPersistent);

		String msg;
		// act
		try {
			msg = vigilant.input(moto);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.INGRESO_SATISFACTORIO, msg);
	}

	@Test public void yaHabiaIngresado() {
		// arrange
		Date ingreso = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);
		Register registryAdmitted = new Register(moto, ingreso);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(vehicleService.findByPlaca(moto.getPlaca())).thenReturn(moto);
		when(registerService.countByTypeVehicle(any(Moto.class))).thenReturn(motoCondiciones.getCupo()-1);
		when(registerService.findByVehicleActive(moto)).thenReturn(registryAdmitted);

		when(parkingService.dateNow()).thenReturn(ingreso);
		Vigilant vigilant = new Vigilant(parkingService, servicesPersistent);

		String msg;
		// act
		try {
			msg = vigilant.input(moto);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.YA_HABIA_INGRESADO, msg);
	}

	@Test public void sinCupoMoto() {
		// arrange
		Date ingreso = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(any(Moto.class))).thenReturn(motoCondiciones.getCupo());

		when(parkingService.dateNow()).thenReturn(ingreso);
		
		Vigilant vigilant = new Vigilant(parkingService, servicesPersistent);

		String msg;
		// act
		try {
			msg = vigilant.input(moto);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.NO_HAY_CUPO, msg);
	}
	
	@Test public void sinCupoCarro() {
		// arrange
		Date ingreso = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Carro carro = new CarroTestDataBuilder().conPlaca("ABC21G").build();
		Conditions carroCondiciones = Conditions.get(carro);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(any(Carro.class))).thenReturn(carroCondiciones.getCupo());
		when(parkingService.dateNow()).thenReturn(ingreso);

		Vigilant vigilant = new Vigilant(parkingService, servicesPersistent);

		String msg;
		// act
		try {
			msg = vigilant.input(carro);
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.NO_HAY_CUPO, msg);
	}
	
}
