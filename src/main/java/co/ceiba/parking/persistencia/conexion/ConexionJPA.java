package co.ceiba.parking.persistencia.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {
	
	private static final String PARKING_PU_TEST = "parking-pu-test";
	private static EntityManagerFactory entityManagerFactory;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory(PARKING_PU_TEST);
	}
	
	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
