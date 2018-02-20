package co.ceiba.parking.persistencia.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import co.ceiba.parking.dominio.repositorio.FacturaRepositorio;
import co.ceiba.parking.dominio.repositorio.RegistroIngresoRepositorio;
import co.ceiba.parking.dominio.repositorio.RegistroRepositorio;
import co.ceiba.parking.dominio.repositorio.RepositorioAdministrador;
import co.ceiba.parking.dominio.repositorio.VehiculoRepositorio;

@Configuration
@EnableJpaRepositories("co.ceiba.dominio.repositorio")
public class AdministradorPersistencia implements RepositorioAdministrador{
	@Autowired
	private FacturaRepositorio facturaRepositorio;
	@Autowired
	private RegistroIngresoRepositorio registroIngresoRepositorio;
	@Autowired
	private RegistroRepositorio registroRepositorio;
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

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
}
