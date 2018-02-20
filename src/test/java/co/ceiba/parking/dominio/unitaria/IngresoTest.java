package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.ceiba.parking.domain.Conditions;
import co.ceiba.parking.domain.EnterVehicle;
import co.ceiba.parking.domain.objects.Carro;
import co.ceiba.parking.domain.objects.Moto;
import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.service.persistent.RegistryAdmittedService;
import co.ceiba.parking.service.persistent.ServicesPersistent;
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

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);

		when(registryAdmittedService.countByTypeVehicle(moto.getTipo())).thenReturn(motoCondiciones.getCupo()-1);
		when(registryAdmittedService.findByVehicle(moto)).thenReturn(new ArrayList<RegistryAdmitted>());
		EnterVehicle enterVehicle = new EnterVehicle(servicesPersistent);

		// act
		enterVehicle.enter(fecha, moto);

		// assert
		assertEquals(Messages.INGRESO_NO_AUTORIZADO, enterVehicle.getMsg());
	}

	@Test public void noIngresarPlacaADiaSabado() {
		// arrange
		Date fecha = FechaTest.crearFecha(17, Calendar.FEBRUARY, 2018); // Sabado 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);

		when(registryAdmittedService.countByTypeVehicle(moto.getTipo())).thenReturn(motoCondiciones.getCupo()-1);
		when(registryAdmittedService.findByVehicle(moto)).thenReturn(new ArrayList<RegistryAdmitted>());

		EnterVehicle enterVehicle = new EnterVehicle(servicesPersistent);

		// act
		enterVehicle.enter(fecha, moto);

		// assert
		assertEquals(Messages.INGRESO_NO_AUTORIZADO, enterVehicle.getMsg());
	}

	@Test public void ingresarPlacaNoADiaDomingo() {
		// arrange
		Date fecha = FechaTest.crearFecha(18, Calendar.FEBRUARY, 2018); // Domingo 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("CBC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);

		when(registryAdmittedService.countByTypeVehicle(moto.getTipo())).thenReturn(motoCondiciones.getCupo()-1);
		when(registryAdmittedService.findByVehicle(moto)).thenReturn(new ArrayList<RegistryAdmitted>());

		EnterVehicle enterVehicle = new EnterVehicle(servicesPersistent);

		// act
		enterVehicle.enter(fecha, moto);

		// assert
		assertEquals(Messages.INGRESO_SATISFACTORIO, enterVehicle.getMsg());
	}

	@Test public void ingresarPlacaADiaLunes() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);

		when(registryAdmittedService.countByTypeVehicle(moto.getTipo())).thenReturn(motoCondiciones.getCupo()-1);
		when(registryAdmittedService.findByVehicle(moto)).thenReturn(new ArrayList<RegistryAdmitted>());

		EnterVehicle enterVehicle = new EnterVehicle(servicesPersistent);

		// act
		enterVehicle.enter(fecha, moto);

		// assert
		assertEquals(Messages.INGRESO_SATISFACTORIO, enterVehicle.getMsg());
	}

	@Test public void yaHabiaIngresado() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);
		RegistryAdmitted registryAdmitted = new RegistryAdmitted(moto, fecha);
		List<RegistryAdmitted> registryAdmittedList = new ArrayList<RegistryAdmitted>();
		registryAdmittedList.add(registryAdmitted);
		
		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);

		when(registryAdmittedService.countByTypeVehicle(moto.getTipo())).thenReturn(motoCondiciones.getCupo()-1);
		when(registryAdmittedService.findByVehicle(moto)).thenReturn(registryAdmittedList);

		EnterVehicle enterVehicle = new EnterVehicle(servicesPersistent);

		// act
		enterVehicle.enter(fecha, moto);

		// assert
		assertEquals(Messages.YA_HABIA_INGRESADO, enterVehicle.getMsg());
	}

	@Test public void sinCupoMoto() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Conditions motoCondiciones = Conditions.get(moto);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);

		when(registryAdmittedService.countByTypeVehicle(moto.getTipo())).thenReturn(motoCondiciones.getCupo());
		when(registryAdmittedService.findByVehicle(moto)).thenReturn(new ArrayList<RegistryAdmitted>());

		EnterVehicle enterVehicle = new EnterVehicle(servicesPersistent);

		// act
		enterVehicle.enter(fecha, moto);

		// assert
		assertEquals(Messages.NO_HAY_CUPO, enterVehicle.getMsg());
	}
	
	@Test public void sinCupoCarro() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Carro carro = new CarroTestDataBuilder().conPlaca("ABC21G").build();
		Conditions carroCondiciones = Conditions.get(carro);

		ServicesPersistent servicesPersistent = mock(ServicesPersistent.class);
		RegistryAdmittedService registryAdmittedService = mock(RegistryAdmittedService.class);

		when(servicesPersistent.getRegistryAdmittedService()).thenReturn(registryAdmittedService);

		when(registryAdmittedService.countByTypeVehicle(carro.getTipo())).thenReturn(carroCondiciones.getCupo());
		when(registryAdmittedService.findByVehicle(carro)).thenReturn(new ArrayList<RegistryAdmitted>());

		EnterVehicle enterVehicle = new EnterVehicle(servicesPersistent);

		// act
		enterVehicle.enter(fecha, carro);

		// assert
		assertEquals(Messages.NO_HAY_CUPO, enterVehicle.getMsg());
	}
	
}
