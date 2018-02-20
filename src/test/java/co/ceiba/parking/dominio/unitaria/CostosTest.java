package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.ceiba.parking.dominio.Condiciones;
import co.ceiba.parking.dominio.Costos;
import co.ceiba.parking.dominio.interfaces.TiemposInterface;
import co.ceiba.parking.dominio.objetos.Carro;
import co.ceiba.parking.dominio.objetos.Moto;
import co.ceiba.parking.persistencia.entidad.Registro;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;
import testdatabuilder.RegistroTestDataBuilder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostosTest {
	
	@Test public void motoConRecargoUnDiaMasTresHoras() {
		// arrange
		int cilindraje = 650;
		Moto moto = new MotoTestDataBuilder().conCilindraje(cilindraje).build();
		Condiciones condiciones = Condiciones.get(moto);
		Registro registro = new RegistroTestDataBuilder().conVehiculo(moto).build();
		TiemposInterface tiempos = mock(TiemposInterface.class);
		
		when(tiempos.getCondicion()).thenReturn(condiciones);	
		when(tiempos.getRegistro()).thenReturn(registro);
		when(tiempos.getHoras()).thenReturn(3);
		when(tiempos.getDias()).thenReturn(1);
		
		Costos costos = new Costos(tiempos);
		double valorDia = condiciones.getValorDia()*1;
		double valorHora = condiciones.getValorHora()*3;
		double valorRecargo = condiciones.getValorRecargo();
		double valorTotal = valorDia + valorHora + valorRecargo;
		// act
		costos.calcular();
		// assert
		assertEquals(valorDia, costos.getValorDias(), 0);
		assertEquals(valorHora, costos.getValorHoras(), 0);
		assertEquals(valorRecargo, costos.getValorRecargo(), 0);
		assertEquals(valorTotal, costos.getValorTotal(), 0);
	}

	@Test public void motoSinRecargoUnDiaMasTresHoras() {
		// arrange
		int cilindraje = 250;
		Moto moto = new MotoTestDataBuilder().conCilindraje(cilindraje).build();
		Condiciones condiciones = Condiciones.get(moto);
		Registro registro = new RegistroTestDataBuilder().conVehiculo(moto).build();
		TiemposInterface tiempos = mock(TiemposInterface.class);
		
		when(tiempos.getCondicion()).thenReturn(condiciones);	
		when(tiempos.getRegistro()).thenReturn(registro);
		when(tiempos.getHoras()).thenReturn(3);
		when(tiempos.getDias()).thenReturn(1);
		
		Costos costos = new Costos(tiempos);
		double valorDia = condiciones.getValorDia()*1;
		double valorHora = condiciones.getValorHora()*3;
		double valorRecargo = 0;
		double valorTotal = valorDia + valorHora + valorRecargo;
		// act
		costos.calcular();
		// assert
		assertEquals(valorDia, costos.getValorDias(), 0);
		assertEquals(valorHora, costos.getValorHoras(), 0);
		assertEquals(valorRecargo, costos.getValorRecargo(), 0);
		assertEquals(valorTotal, costos.getValorTotal(), 0);
	}

	@Test public void carroSinRecargoUnDiaMasTresHoras() {
		// arrange
		int cilindraje = 1800;
		Carro carro = new CarroTestDataBuilder().conCilindraje(cilindraje).build();
		Condiciones condiciones = Condiciones.get(carro);
		Registro registro = new RegistroTestDataBuilder().conVehiculo(carro).build();
		TiemposInterface tiempos = mock(TiemposInterface.class);
		
		when(tiempos.getCondicion()).thenReturn(condiciones);	
		when(tiempos.getRegistro()).thenReturn(registro);
		when(tiempos.getHoras()).thenReturn(3);
		when(tiempos.getDias()).thenReturn(1);
		
		Costos costos = new Costos(tiempos);
		double valorDia = condiciones.getValorDia()*1;
		double valorHora = condiciones.getValorHora()*3;
		double valorRecargo = 0;
		double valorTotal = valorDia + valorHora;
		// act
		costos.calcular();
		// assert
		assertEquals(valorDia, costos.getValorDias(), 0);
		assertEquals(valorHora, costos.getValorHoras(), 0);
		assertEquals(valorRecargo, costos.getValorRecargo(), 0);
		assertEquals(valorTotal, costos.getValorTotal(), 0);
	}
	
}
