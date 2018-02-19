package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.ceiba.parking.dominio.Condiciones;
import co.ceiba.parking.dominio.RegistroIngreso;
import co.ceiba.parking.dominio.Ingreso;
import co.ceiba.parking.dominio.objetos.Carro;
import co.ceiba.parking.dominio.objetos.Moto;
import co.ceiba.parking.dominio.repositorio.RegistroIngresoRepositorio;
import co.ceiba.parking.dominio.repositorio.RepositorioAdministrador;
import co.ceiba.parking.mensajes.Mensajes;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;
import testutilidades.FechaTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IngresoTest {

	@Test public void noIngresarPlacaADiaDomingo() {
		// arrange
		Date fecha = FechaTest.crearFecha(18, Calendar.FEBRUARY, 2018); // Domingo 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Condiciones motoCondiciones = Condiciones.get(moto);

		RepositorioAdministrador repositorioAdministrador = mock(RepositorioAdministrador.class);
		RegistroIngresoRepositorio registroIngresoRepositorio = mock(RegistroIngresoRepositorio.class);

		when(repositorioAdministrador.getIngresadosRepositorio()).thenReturn(registroIngresoRepositorio);

		when(registroIngresoRepositorio.obtenerCantidadPorTipoVehiculo(moto)).thenReturn(motoCondiciones.getCupo()-1);
		when(registroIngresoRepositorio.obtenerPorVehiculo(moto)).thenReturn(null);

		Ingreso ingreso = new Ingreso(repositorioAdministrador);

		// act
		ingreso.ingresar(fecha, moto);

		// assert
		assertEquals(Mensajes.INGRESO_NO_AUTORIZADO, ingreso.getMsg());
	}

	@Test public void noIngresarPlacaADiaSabado() {
		// arrange
		Date fecha = FechaTest.crearFecha(17, Calendar.FEBRUARY, 2018); // Sabado 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Condiciones motoCondiciones = Condiciones.get(moto);

		RepositorioAdministrador repositorioAdministrador = mock(RepositorioAdministrador.class);
		RegistroIngresoRepositorio registroIngresoRepositorio = mock(RegistroIngresoRepositorio.class);

		when(repositorioAdministrador.getIngresadosRepositorio()).thenReturn(registroIngresoRepositorio);

		when(registroIngresoRepositorio.obtenerCantidadPorTipoVehiculo(moto)).thenReturn(motoCondiciones.getCupo()-1);
		when(registroIngresoRepositorio.obtenerPorVehiculo(moto)).thenReturn(null);

		Ingreso ingreso = new Ingreso(repositorioAdministrador);

		// act
		ingreso.ingresar(fecha, moto);

		// assert
		assertEquals(Mensajes.INGRESO_NO_AUTORIZADO, ingreso.getMsg());
	}

	@Test public void ingresarPlacaNoADiaDomingo() {
		// arrange
		Date fecha = FechaTest.crearFecha(18, Calendar.FEBRUARY, 2018); // Domingo 18/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("CBC21G").build();
		Condiciones motoCondiciones = Condiciones.get(moto);

		RepositorioAdministrador repositorioAdministrador = mock(RepositorioAdministrador.class);
		RegistroIngresoRepositorio registroIngresoRepositorio = mock(RegistroIngresoRepositorio.class);

		when(repositorioAdministrador.getIngresadosRepositorio()).thenReturn(registroIngresoRepositorio);

		when(registroIngresoRepositorio.obtenerCantidadPorTipoVehiculo(moto)).thenReturn(motoCondiciones.getCupo()-1);
		when(registroIngresoRepositorio.obtenerPorVehiculo(moto)).thenReturn(null);

		Ingreso ingreso = new Ingreso(repositorioAdministrador);

		// act
		ingreso.ingresar(fecha, moto);

		// assert
		assertEquals(Mensajes.INGRESO, ingreso.getMsg());
	}

	@Test public void ingresarPlacaADiaLunes() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Condiciones motoCondiciones = Condiciones.get(moto);

		RepositorioAdministrador repositorioAdministrador = mock(RepositorioAdministrador.class);
		RegistroIngresoRepositorio registroIngresoRepositorio = mock(RegistroIngresoRepositorio.class);

		when(repositorioAdministrador.getIngresadosRepositorio()).thenReturn(registroIngresoRepositorio);

		when(registroIngresoRepositorio.obtenerCantidadPorTipoVehiculo(moto)).thenReturn(motoCondiciones.getCupo()-1);
		when(registroIngresoRepositorio.obtenerPorVehiculo(moto)).thenReturn(null);

		Ingreso ingreso = new Ingreso(repositorioAdministrador);

		// act
		ingreso.ingresar(fecha, moto);

		// assert
		assertEquals(Mensajes.INGRESO, ingreso.getMsg());
	}

	@Test public void yaHabiaIngresado() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Condiciones motoCondiciones = Condiciones.get(moto);
		RegistroIngreso ingresado = new RegistroIngreso(moto, fecha);

		RepositorioAdministrador repositorioAdministrador = mock(RepositorioAdministrador.class);
		RegistroIngresoRepositorio registroIngresoRepositorio = mock(RegistroIngresoRepositorio.class);

		when(repositorioAdministrador.getIngresadosRepositorio()).thenReturn(registroIngresoRepositorio);

		when(registroIngresoRepositorio.obtenerCantidadPorTipoVehiculo(moto)).thenReturn(motoCondiciones.getCupo()-1);
		when(registroIngresoRepositorio.obtenerPorVehiculo(moto)).thenReturn(ingresado);

		Ingreso ingreso = new Ingreso(repositorioAdministrador);

		// act
		ingreso.ingresar(fecha, moto);

		// assert
		assertEquals(Mensajes.YA_HABIA_INGRESADO, ingreso.getMsg());
	}

	@Test public void sinCupoMoto() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Condiciones motoCondiciones = Condiciones.get(moto);

		RepositorioAdministrador repositorioAdministrador = mock(RepositorioAdministrador.class);
		RegistroIngresoRepositorio registroIngresoRepositorio = mock(RegistroIngresoRepositorio.class);

		when(repositorioAdministrador.getIngresadosRepositorio()).thenReturn(registroIngresoRepositorio);

		when(registroIngresoRepositorio.obtenerCantidadPorTipoVehiculo(moto)).thenReturn(motoCondiciones.getCupo());
		when(registroIngresoRepositorio.obtenerPorVehiculo(moto)).thenReturn(null);

		Ingreso ingreso = new Ingreso(repositorioAdministrador);

		// act
		ingreso.ingresar(fecha, moto);

		// assert
		assertEquals(Mensajes.NO_HAY_CUPO, ingreso.getMsg());
	}
	
	@Test public void sinCupoCarro() {
		// arrange
		Date fecha = FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // Lunes 19/febrero/2018
		Carro carro = new CarroTestDataBuilder().conPlaca("ABC21G").build();
		Condiciones carroCondiciones = Condiciones.get(carro);

		RepositorioAdministrador repositorioAdministrador = mock(RepositorioAdministrador.class);
		RegistroIngresoRepositorio registroIngresoRepositorio = mock(RegistroIngresoRepositorio.class);

		when(repositorioAdministrador.getIngresadosRepositorio()).thenReturn(registroIngresoRepositorio);

		when(registroIngresoRepositorio.obtenerCantidadPorTipoVehiculo(carro)).thenReturn(carroCondiciones.getCupo());
		when(registroIngresoRepositorio.obtenerPorVehiculo(carro)).thenReturn(null);

		Ingreso ingreso = new Ingreso(repositorioAdministrador);

		// act
		ingreso.ingresar(fecha, carro);

		// assert
		assertEquals(Mensajes.NO_HAY_CUPO, ingreso.getMsg());
	}
	
}
