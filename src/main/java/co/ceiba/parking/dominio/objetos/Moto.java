package co.ceiba.parking.dominio.objetos;

import co.ceiba.parking.persistencia.entidad.Vehiculo;

public class Moto extends Vehiculo{
	public static final String TIPO = "moto";

	public Moto(String placa, int cilindraje) {
		super();
		this.setTipo(TIPO);
		this.setPlaca(placa);
		this.setCilindraje(cilindraje);
	}
	
}
