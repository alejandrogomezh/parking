package co.ceiba.parking.dominio.repositorio;

import co.ceiba.parking.dominio.objetos.Vehiculo;

public interface VehiculoRepositorio {
	Vehiculo obtener(Vehiculo vehiculo);
	Vehiculo agregar(Vehiculo vehiculo);
}
