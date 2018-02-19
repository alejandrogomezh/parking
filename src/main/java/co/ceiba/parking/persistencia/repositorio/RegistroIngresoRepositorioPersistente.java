package co.ceiba.parking.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.ceiba.parking.dominio.RegistroIngreso;
import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.dominio.repositorio.RegistroIngresoRepositorio;
import co.ceiba.parking.persistencia.constructor.RegistroIngresoConstructor;
import co.ceiba.parking.persistencia.entidad.RegistroIngresoEntity;

public class RegistroIngresoRepositorioPersistente implements RegistroIngresoRepositorio{
	private static final String CANTIDAD_POR_TIPO_VEHICULO = "RegistroIngreso.cantidadPorTipoVehiculo";
	private static final String OBTENER_POR_VEHICULO = "RegistroIngreso.obtenerPorPlaca";
	
	private EntityManager entityManager;

	public RegistroIngresoRepositorioPersistente(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	

	@SuppressWarnings("unchecked")
	@Override
	public int obtenerCantidadPorTipoVehiculo(Vehiculo vehiculo) {
		if(vehiculo == null) return -1;
		
		Query query = entityManager.createNamedQuery(CANTIDAD_POR_TIPO_VEHICULO);
		query.setParameter("tipo", vehiculo.getTipo());

		List<Integer> resultList = query.getResultList();
		return resultList.isEmpty() ? -1 : resultList.get(0);

	}

	@SuppressWarnings("unchecked")
	@Override
	public RegistroIngreso obtenerPorVehiculo(Vehiculo vehiculo) {
		if(vehiculo == null) return null;
		
		Query query = entityManager.createNamedQuery(OBTENER_POR_VEHICULO);
		query.setParameter("placa", vehiculo.getPlaca());

		List<RegistroIngresoEntity> resultList = query.getResultList();
		return resultList.isEmpty() ? null : RegistroIngresoConstructor.aDominio(resultList.get(0));
	}

	@Override
	public RegistroIngreso agregar(RegistroIngreso ingresar) {
		RegistroIngresoEntity registroIngresoEntity = RegistroIngresoConstructor.aEntidad(ingresar);
		entityManager.persist(registroIngresoEntity);
		return ingresar.setSelfEntity(registroIngresoEntity);
	}

}
