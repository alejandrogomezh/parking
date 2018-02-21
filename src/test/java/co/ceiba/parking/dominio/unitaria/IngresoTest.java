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
import co.ceiba.parking.domain.objects.Carro;
import co.ceiba.parking.domain.objects.Moto;
import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.service.RegistryAdmittedService;
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
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registryAdmittedService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);
		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		// act
		vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());

		// assert
		assertEquals(Messages.INGRESO_NO_AUTORIZADO, vigilant.getMsg());
	}

	@Test public void noIngresarPlacaADiaSabado() {
		// arrange
		Date fecha = FechaTest.crearFecha(17, Calendar.FEBRUARY, 2018); // Sabado 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registryAdmittedService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		// act
		vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());

		// assert
		assertEquals(Messages.INGRESO_NO_AUTORIZADO, vigilant.getMsg());
	}

	@Test public void ingresarPlacaNoADiaDomingo() {
		// arrange
		Date fecha = FechaTest.crearFecha(18, Calendar.FEBRUARY, 2018); // Domingo 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("CBC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registryAdmittedService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		// act
		vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());

		// assert
		assertEquals(Messages.INGRESO_SATISFACTORIO, vigilant.getMsg());
	}

	@Test public void ingresarPlacaADiaLunes() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registryAdmittedService.countByTypeVehicle(moto)).thenReturn(motoCondiciones.getCupo()-1);

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		// act
		vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());

		// assert
		assertEquals(Messages.INGRESO_SATISFACTORIO, vigilant.getMsg());
	}

	@Test public void yaHabiaIngresado() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);
		RegistryAdmitted registryAdmitted = new RegistryAdmitted(moto, fecha);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(vehicleService.findByTipoAndPlaca(moto.getTipo(), moto.getPlaca())).thenReturn(moto);
		when(registryAdmittedService.countByTypeVehicle(any(Moto.class))).thenReturn(motoCondiciones.getCupo()-1);
		when(registryAdmittedService.findByVehicle(moto)).thenReturn(registryAdmitted);

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		// act
		vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());

		// assert
		assertEquals(Messages.YA_HABIA_INGRESADO, vigilant.getMsg());
	}

	@Test public void sinCupoMoto() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registryAdmittedService.countByTypeVehicle(any(Moto.class))).thenReturn(motoCondiciones.getCupo());

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		// act
		vigilant.ingreso(moto.getTipo(), moto.getPlaca(), moto.getCilindraje());

		// assert
		assertEquals(Messages.NO_HAY_CUPO, vigilant.getMsg());
	}
	
	@Test public void sinCupoCarro() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Carro carro = new CarroTestDataBuilder().conPlaca("ABC21G").build();
		Conditions carroCondiciones = Conditions.get(carro);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);
		VehicleService vehicleService = mock(VehicleService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		when(registryAdmittedService.countByTypeVehicle(any(Carro.class))).thenReturn(carroCondiciones.getCupo());

		Vigilant vigilant = new Vigilant(servicesPersistent) {
			protected Date dateNow() {return fecha;};
		};

		// act
		vigilant.ingreso(carro.getTipo(), carro.getPlaca(), carro.getCilindraje());

		// assert
		assertEquals(Messages.NO_HAY_CUPO, vigilant.getMsg());
	}
	
}
