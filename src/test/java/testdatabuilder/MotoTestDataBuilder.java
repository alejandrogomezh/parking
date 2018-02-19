package testdatabuilder;

import co.ceiba.parking.dominio.objetos.Moto;

public class MotoTestDataBuilder {
	private static final String PLACA = "ASK65F";
	private static final int CILINDRAJE = 250;
	
	private String placa;
	private int cilindraje;
	
	public MotoTestDataBuilder() {
		this.placa = PLACA;
		this.cilindraje = CILINDRAJE;
	}

	public MotoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public MotoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public Moto build() {
		return new Moto(placa, cilindraje);
	}
}
