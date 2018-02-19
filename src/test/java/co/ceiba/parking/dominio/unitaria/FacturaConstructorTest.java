package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.ceiba.parking.dominio.objetos.Factura;

import co.ceiba.parking.persistencia.constructor.FacturaConstructor;
import co.ceiba.parking.persistencia.entidad.FacturaEntity;
import testdatabuilder.FacturaTestDataBuilder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FacturaConstructorTest {

	@Test public void facturaConvertirDominioAEntidad() {
		// arrange
		Factura esperado = new FacturaTestDataBuilder().build();
		// act
		FacturaEntity entidad = FacturaConstructor.aEntidad(esperado);
		// assert
		assertEquals(esperado.getRegistro().getSelfEntity(), entidad.getRegistro());
		assertEquals(esperado.getDias(), entidad.getDias(), 0);
		assertEquals(esperado.getHoras(), entidad.getHoras(), 0);
		assertEquals(esperado.getValorDias(), entidad.getValorDias(), 0);
		assertEquals(esperado.getValorHoras(), entidad.getValorHoras(), 0);
		assertEquals(esperado.getValorRecargo(), entidad.getValorRecargo(), 0);
		assertEquals(esperado.getValorTotal(), entidad.getValorTotal(), 0);

	}
	
	@Test public void facturaConvertirEntidadADominio() {
		// arrange
		Factura esperado = new FacturaTestDataBuilder().build();
		FacturaEntity entidad = FacturaConstructor.aEntidad(esperado);
		// act
		Factura dominio = FacturaConstructor.aDominio(entidad);
		// assert
		assertEquals(esperado.getRegistro(), dominio.getRegistro());
		assertEquals(esperado.getDias(), dominio.getDias(), 0);
		assertEquals(esperado.getHoras(), dominio.getHoras(), 0);
		assertEquals(esperado.getValorDias(), dominio.getValorDias(), 0);
		assertEquals(esperado.getValorHoras(), dominio.getValorHoras(), 0);
		assertEquals(esperado.getValorRecargo(), dominio.getValorRecargo(), 0);
		assertEquals(esperado.getValorTotal(), dominio.getValorTotal(), 0);
	}

	
}
