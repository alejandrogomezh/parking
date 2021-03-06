package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.ceiba.parking.domain.Calculator;
import co.ceiba.parking.domain.Conditions;
import co.ceiba.parking.domain.Cost;
import co.ceiba.parking.domain.interfaces.TimeInterface;
import co.ceiba.parking.domain.objects.Carro;
import co.ceiba.parking.domain.objects.Moto;
import co.ceiba.parking.domain.objects.Register;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;
import testdatabuilder.RegisterTestDataBuilder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostosTest {
	
	@Test public void motoConRecargoUnDiaMasTresHoras() {
		// arrange
		int cilindraje = 650;
		Moto moto = new MotoTestDataBuilder().conCilindraje(cilindraje).build();
		Conditions conditions = Conditions.get(moto);
		Register register = new RegisterTestDataBuilder().conVehiculo(moto).build();
		TimeInterface time = mock(TimeInterface.class);
		Calculator calculator = new Calculator(conditions, register);
		
		when(time.getHoras()).thenReturn(3);
		when(time.getDias()).thenReturn(1);
		
		
		double valorDia = conditions.getValorDia()*1;
		double valorHora = conditions.getValorHora()*3;
		double valorRecargo = conditions.getValorRecargo();
		double valorTotal = valorDia + valorHora + valorRecargo;
		
		// act
		Cost cost = calculator.cost(time);
		// assert
		assertEquals(valorDia, cost.getValorDias(), 0);
		assertEquals(valorHora, cost.getValorHoras(), 0);
		assertEquals(valorRecargo, cost.getValorRecargo(), 0);
		assertEquals(valorTotal, cost.getValorTotal(), 0);
	}

	@Test public void motoSinRecargoUnDiaMasTresHoras() {
		// arrange
		int cilindraje = 250;
		Moto moto = new MotoTestDataBuilder().conCilindraje(cilindraje).build();
		Conditions conditions = Conditions.get(moto);
		Register register = new RegisterTestDataBuilder().conVehiculo(moto).build();
		TimeInterface time = mock(TimeInterface.class);
		Calculator calculator = new Calculator(conditions, register);
		
		when(time.getHoras()).thenReturn(3);
		when(time.getDias()).thenReturn(1);

		double valorDia = conditions.getValorDia()*1;
		double valorHora = conditions.getValorHora()*3;
		double valorRecargo = 0;
		double valorTotal = valorDia + valorHora + valorRecargo;
		
		// act
		Cost cost = calculator.cost(time);
		// assert
		assertEquals(valorDia, cost.getValorDias(), 0);
		assertEquals(valorHora, cost.getValorHoras(), 0);
		assertEquals(valorRecargo, cost.getValorRecargo(), 0);
		assertEquals(valorTotal, cost.getValorTotal(), 0);
	}

	@Test public void carroSinRecargoUnDiaMasTresHoras() {
		// arrange
		int cilindraje = 1800;
		Carro carro = new CarroTestDataBuilder().conCilindraje(cilindraje).build();
		Conditions conditions = Conditions.get(carro);
		Register register = new RegisterTestDataBuilder().conVehiculo(carro).build();
		TimeInterface time = mock(TimeInterface.class);
		Calculator calculator = new Calculator(conditions, register);
		
		when(time.getHoras()).thenReturn(3);
		when(time.getDias()).thenReturn(1);
		
		double valorDia = conditions.getValorDia()*1;
		double valorHora = conditions.getValorHora()*3;
		double valorRecargo = 0;
		double valorTotal = valorDia + valorHora;
		// act
		Cost cost = calculator.cost(time);
		// assert
		assertEquals(valorDia, cost.getValorDias(), 0);
		assertEquals(valorHora, cost.getValorHoras(), 0);
		assertEquals(valorRecargo, cost.getValorRecargo(), 0);
		assertEquals(valorTotal, cost.getValorTotal(), 0);
	}
	
}
