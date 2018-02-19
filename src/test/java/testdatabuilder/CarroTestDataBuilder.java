package testdatabuilder;

import co.ceiba.parking.dominio.objetos.Carro;

public class CarroTestDataBuilder {
	private static final String PLACA = "OPQ123";
	private static final int CILINDRAJE = 1800;
	
	private String placa;
	private int cilindraje;
	
	public CarroTestDataBuilder() {
		this.placa = PLACA;
		this.cilindraje = CILINDRAJE;
	}

	public CarroTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public CarroTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public Carro build() {
		return new Carro(placa, cilindraje);
	}
}
