package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.ceiba.parking.domain.Conditions;
import co.ceiba.parking.domain.Vigilant;
import co.ceiba.parking.domain.excepcion.ParkingException;
import co.ceiba.parking.domain.objects.Carro;
import co.ceiba.parking.domain.objects.Moto;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.service.RegisterService;
import co.ceiba.parking.persistent.service.ServicesPersistent;
import co.ceiba.parking.persistent.service.VehicleService;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;
import testutilidades.FechaTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IngresoTest {

	@Test public void noIngresarPlacaADiaDomingo() {
		// arrange
		Date fecha = FechaTest.crearFecha(18, Calendar.FEBRUARY, 2018); // Domingo 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);
		
		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegisterService registerService = mock(RegisterService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);
		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};
		
		String msg;
		// act
		try {
			msg = vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.INGRESO_NO_AUTORIZADO, msg);
	}

	@Test public void noIngresarPlacaADiaSabado() {
		// arrange
		Date fecha = FechaTest.crearFecha(17, Calendar.FEBRUARY, 2018); // Sabado 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegisterService registerService = mock(RegisterService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		String msg;
		// act
		try {
			msg = vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.INGRESO_NO_AUTORIZADO, msg);
	}

	@Test public void ingresarPlacaNoADiaDomingo() {
		// arrange
		Date fecha = FechaTest.crearFecha(18, Calendar.FEBRUARY, 2018); // Domingo 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("CBC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegisterService registerService = mock(RegisterService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		String msg;
		// act
		try {
			msg = vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.INGRESO_SATISFACTORIO, msg);
	}

	@Test public void ingresarPlacaADiaLunes() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegisterService registerService = mock(RegisterService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		String msg;
		// act
		try {
			msg = vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.INGRESO_SATISFACTORIO, msg);
	}

	@Test public void yaHabiaIngresado() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);
		Register registryAdmitted = new Register(moto, fecha);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegisterService registerService = mock(RegisterService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(vehicleService.findByPlaca(moto.getPlaca())).thenReturn(moto);
		when(registerService.countByTypeVehicle(any(Moto.class))).thenReturn(motoCondiciones.getCupo()-1);
		when(registerService.findByVehicleActive(moto)).thenReturn(registryAdmitted);

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		String msg;
		// act
		try {
			msg = vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.YA_HABIA_INGRESADO, msg);
	}

	@Test public void sinCupoMoto() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegisterService registerService = mock(RegisterService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(any(Moto.class))).thenReturn(motoCondiciones.getCupo());

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		String msg;
		// act
		try {
			msg = vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.NO_HAY_CUPO, msg);
	}
	
	@Test public void sinCupoCarro() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Carro carro = new CarroTestDataBuilder().conPlaca("ABC21G").build();
		Conditions carroCondiciones = Conditions.get(carro);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegisterService registerService = mock(RegisterService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registerService.countByTypeVehicle(any(Carro.class))).thenReturn(carroCondiciones.getCupo());

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		String msg;
		// act
		try {
			msg = vigilant.ingreso(carro.getTipo(), carro.getPlaca(), carro.getCilindraje());
		} catch (ParkingException e) {
			msg = e.getMessage();
		}
		// assert	
		assertEquals(Messages.NO_HAY_CUPO, msg);
	}
	
}
