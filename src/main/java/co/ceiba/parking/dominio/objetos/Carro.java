package co.ceiba.parking.dominio.objetos;

import co.ceiba.parking.persistencia.entidad.Vehiculo;

public class Carro extends Vehiculo{
	public static final String TIPO = "carro";

	public Carro(String placa, int cilindraje) {
		super();
		this.setTipo(TIPO);
		this.setPlaca(placa);
		this.setCilindraje(cilindraje);
	}
	
}
