package co.ceiba.parking.dominio.repositorio;

public interface RepositorioAdministrador {
	FacturaRepositorio getFacturaRepositorio();
	RegistroIngresoRepositorio getIngresadosRepositorio();
	RegistroRepositorio getRegistroRepositorio();
	VehiculoRepositorio getVehiculoRepositorio();
}
