package co.ceiba.parking.domain.objects;

public class Moto extends Vehicle{
	public static final String TIPO = "moto";

	public Moto(String placa, int cilindraje) {
		super(TIPO, placa, cilindraje);
	}
	
}
