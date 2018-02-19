package co.ceiba.parking.persistencia.repositorio;

import javax.persistence.EntityManager;

import co.ceiba.parking.dominio.objetos.Registro;
import co.ceiba.parking.dominio.repositorio.RegistroRepositorio;
import co.ceiba.parking.persistencia.constructor.RegistroConstructor;
import co.ceiba.parking.persistencia.entidad.RegistroEntity;

public class RegistroRepositorioPersistente implements RegistroRepositorio{

	private EntityManager entityManager;

	public RegistroRepositorioPersistente(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Registro agregar(Registro registro) {
		RegistroEntity registroEntity = RegistroConstructor.aEntidad(registro);
		entityManager.persist(registroEntity);
		return registro.setSelfEntity(registroEntity);
	}	

}
