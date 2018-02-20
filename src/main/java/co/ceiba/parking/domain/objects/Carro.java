package co.ceiba.parking.domain.objects;


public class Carro extends Vehicle{
	public static final String TIPO = "carro";

	public Carro(String placa, int cilindraje) {
		super(TIPO, placa, cilindraje);
	}
	
}
