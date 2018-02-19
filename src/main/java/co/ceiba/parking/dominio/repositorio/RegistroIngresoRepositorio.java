package co.ceiba.parking.dominio.repositorio;

import co.ceiba.parking.dominio.RegistroIngreso;
import co.ceiba.parking.dominio.objetos.Vehiculo;

public interface RegistroIngresoRepositorio {
	int obtenerCantidadPorTipoVehiculo(Vehiculo vehiculo);
	RegistroIngreso obtenerPorVehiculo(Vehiculo vehiculo);
	RegistroIngreso agregar(RegistroIngreso ingresar);
}
