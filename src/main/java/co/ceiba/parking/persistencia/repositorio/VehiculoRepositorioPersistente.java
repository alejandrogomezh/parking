package co.ceiba.parking.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.dominio.repositorio.VehiculoRepositorio;
import co.ceiba.parking.persistencia.constructor.VehiculoConstructor;
import co.ceiba.parking.persistencia.entidad.VehiculoEntity;

public class VehiculoRepositorioPersistente implements VehiculoRepositorio{
	private static final String OBTENER_POR_TIPO_Y_PLACA = "Vehiculo.obtenerPorTipoYPlaca";

	private EntityManager entityManager;

	public VehiculoRepositorioPersistente(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vehiculo obtener(Vehiculo vehiculo) {
		if(vehiculo == null) return null;
		
		Query query = entityManager.createNamedQuery(OBTENER_POR_TIPO_Y_PLACA);
		query.setParameter("placa", vehiculo.getPlaca());
		
		List<VehiculoEntity> resultList = query.getResultList();
		return resultList.isEmpty() ? null : VehiculoConstructor.aDominio(resultList.get(0));
	}	

	@Override
	public Vehiculo agregar(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = VehiculoConstructor.aEntidad(vehiculo);
		entityManager.persist(vehiculoEntity);
		return vehiculo.setSelfEntity(vehiculoEntity);
	}

}
