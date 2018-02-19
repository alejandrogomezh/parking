package co.ceiba.parking.persistencia.administrador;

import javax.persistence.EntityManager;

import co.ceiba.parking.dominio.repositorio.FacturaRepositorio;
import co.ceiba.parking.dominio.repositorio.RegistroIngresoRepositorio;
import co.ceiba.parking.dominio.repositorio.RegistroRepositorio;
import co.ceiba.parking.dominio.repositorio.RepositorioAdministrador;
import co.ceiba.parking.dominio.repositorio.VehiculoRepositorio;
import co.ceiba.parking.persistencia.conexion.ConexionJPA;
import co.ceiba.parking.persistencia.repositorio.FacturaRepositorioPersistente;
import co.ceiba.parking.persistencia.repositorio.RegistroIngresoRepositorioPersistente;
import co.ceiba.parking.persistencia.repositorio.RegistroRepositorioPersistente;
import co.ceiba.parking.persistencia.repositorio.VehiculoRepositorioPersistente;

public class AdministradorPersistencia implements RepositorioAdministrador{
	private EntityManager entityManager;
	private FacturaRepositorio facturaRepositorio;
	private RegistroIngresoRepositorio registroIngresoRepositorio;
	private RegistroRepositorio registroRepositorio;
	private VehiculoRepositorio vehiculoRepositorio;
	
	public AdministradorPersistencia() {
		entityManager = new ConexionJPA().createEntityManager();
		facturaRepositorio = new FacturaRepositorioPersistente(entityManager);
		registroIngresoRepositorio = new RegistroIngresoRepositorioPersistente(entityManager);
		registroRepositorio = new RegistroRepositorioPersistente(entityManager);
		vehiculoRepositorio = new VehiculoRepositorioPersistente(entityManager);
	}

	@Override
	public FacturaRepositorio getFacturaRepositorio() {
		return facturaRepositorio;
	}

	@Override
	public RegistroIngresoRepositorio getIngresadosRepositorio() {
		return registroIngresoRepositorio;
	}

	@Override
	public RegistroRepositorio getRegistroRepositorio() {
		return registroRepositorio;
	}

	@Override
	public VehiculoRepositorio getVehiculoRepositorio() {
		return vehiculoRepositorio;
	}

	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
