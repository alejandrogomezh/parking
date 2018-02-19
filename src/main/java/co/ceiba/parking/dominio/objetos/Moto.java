package co.ceiba.parking.dominio.objetos;

public class Moto extends Vehiculo{
	public static final String TIPO = "moto";

	public Moto(String placa, int cilindraje) {
		super(TIPO, placa, cilindraje);
	}
	
}
