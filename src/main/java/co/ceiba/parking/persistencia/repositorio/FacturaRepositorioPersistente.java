package co.ceiba.parking.persistencia.repositorio;

import javax.persistence.EntityManager;

import co.ceiba.parking.dominio.objetos.Factura;
import co.ceiba.parking.dominio.repositorio.FacturaRepositorio;
import co.ceiba.parking.persistencia.constructor.FacturaConstructor;
import co.ceiba.parking.persistencia.entidad.FacturaEntity;

public class FacturaRepositorioPersistente implements FacturaRepositorio {

	private EntityManager entityManager;

	public FacturaRepositorioPersistente(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	

	@Override
	public Factura agregar(Factura factura) {
		FacturaEntity facturaEntity = FacturaConstructor.aEntidad(factura);
		entityManager.persist(facturaEntity);
		return factura.setSelfEntity(facturaEntity);
	}
	
}
