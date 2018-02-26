package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.ceiba.parking.domain.Calculator;
import co.ceiba.parking.domain.Conditions;
import co.ceiba.parking.domain.Time;
import co.ceiba.parking.domain.objects.Carro;
import co.ceiba.parking.domain.objects.Register;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.RegisterTestDataBuilder;
import testutilidades.FechaTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TiemposTest {
	
	@Test public void unDiaMasTresHoras() {
		// arrange
		Date ingreso = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 12, 0);
		Date salida = FechaTest.crearFechaConHora(25, Calendar.MAY, 2018, 15, 0);
		Carro carro = new CarroTestDataBuilder().build();
		Conditions conditions = Conditions.get(carro);
		Register register = new RegisterTestDataBuilder()
				.conIngreso(ingreso)
				.conSalida(salida)
				.build();
		Calculator calculator = new Calculator(conditions, register);
		// act
		Time time = calculator.time();
		// assert
		assertEquals(1, time.getDias());
		assertEquals(3, time.getHoras());
	}
	
	@Test public void unDiaMasCuatroHorasPor1Minuto() {
		// arrange
		Date ingreso = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 12, 0);
		Date salida = FechaTest.crearFechaConHora(25, Calendar.MAY, 2018, 15, 1);
		Carro carro = new CarroTestDataBuilder().build();
		Conditions conditions = Conditions.get(carro);
		Register register = new RegisterTestDataBuilder()
				.conIngreso(ingreso)
				.conSalida(salida)
				.build();
		Calculator calculator = new Calculator(conditions, register);
		// act
		Time time = calculator.time();
		// assert
		assertEquals(1, time.getDias());
		assertEquals(4, time.getHoras());
	}
	
	@Test public void unDiaDesde10Horas() {
		// arrange
		Date ingreso = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 7, 0);
		Date salida = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 17, 0);
		Carro carro = new CarroTestDataBuilder().build();
		Conditions conditions = Conditions.get(carro);
		Register register = new RegisterTestDataBuilder()
				.conIngreso(ingreso)
				.conSalida(salida)
				.build();
		Calculator calculator = new Calculator(conditions, register);
		// act
		Time time = calculator.time();
		// assert
		assertEquals(1, time.getDias());
		assertEquals(0, time.getHoras());
	}

	@Test public void unDiaDesde9Horas() {
		// arrange
		Date ingreso = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 7, 0);
		Date salida = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 16, 0);
		Carro carro = new CarroTestDataBuilder().build();
		Conditions conditions = Conditions.get(carro);
		Register register = new RegisterTestDataBuilder()
				.conIngreso(ingreso)
				.conSalida(salida)
				.build();
		Calculator calculator = new Calculator(conditions, register);
		// act
		Time time = calculator.time();
		// assert
		assertEquals(1, time.getDias());
		assertEquals(0, time.getHoras());
	}

	@Test public void ochoHoras() {
		// arrange
		Date ingreso = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 7, 0);
		Date salida = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 15, 0);
		Carro carro = new CarroTestDataBuilder().build();
		Conditions conditions = Conditions.get(carro);
		Register register = new RegisterTestDataBuilder()
				.conIngreso(ingreso)
				.conSalida(salida)
				.build();
		Calculator calculator = new Calculator(conditions, register);
		// act
		Time time = calculator.time();
		// assert
		assertEquals(0, time.getDias());
		assertEquals(8, time.getHoras());
	}

}
