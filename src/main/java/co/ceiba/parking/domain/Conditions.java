package co.ceiba.parking.domain;

import co.ceiba.parking.domain.objects.Carro;
import co.ceiba.parking.domain.objects.Moto;
import co.ceiba.parking.domain.objects.Vehicle;

public class Conditions {	
	private static final int HORAS_DIA_MINIMO = 9;
	private static final int CARRO_CILINDRAJE_MAX_SIN_RECARGO = -1;
	private static final int CARRO_CUPO = 20;
	private static final double CARRO_VALOR_HORA = 1000;
	private static final double CARRO_VALOR_DIA = 8000;
	private static final double CARRO_VALOR_RECARGO = 0;
	
	private static final int MOTO_CILINDRAJE_RECARGO = 500;
	private static final int MOTO_CUPO = 10;
	private static final double MOTO_VALOR_HORA = 500;
	private static final double MOTO_VALOR_DIA = 4000;
	private static final double MOTO_VALOR_RECARGO = 2000;
	
	private static final Conditions carroCondiciones;
	private static final Conditions motoCondiciones;

	private String tipo;
	private int horasDiaMinimo;
	private int cilindrajeMaxSinRecargo;
	private int cupo;
	private double valorHora;
	private double valorDia;
	private double valorRecargo;
	
	static {
		carroCondiciones = new Conditions(
				Carro.TIPO,
				HORAS_DIA_MINIMO,
				CARRO_CILINDRAJE_MAX_SIN_RECARGO,
				CARRO_CUPO,
				CARRO_VALOR_HORA,
				CARRO_VALOR_DIA,
				CARRO_VALOR_RECARGO
				);
		
		motoCondiciones = new Conditions(
				Moto.TIPO,
				HORAS_DIA_MINIMO,
				MOTO_CILINDRAJE_RECARGO,
				MOTO_CUPO,
				MOTO_VALOR_HORA,
				MOTO_VALOR_DIA,
				MOTO_VALOR_RECARGO
				);
	}
	
	public Conditions(String tipo, int horasDiaMinimo, int cilindrajeMaxSinRecargo, int cupo, double valorHora, double valorDia,
			double valorRecargo) {
		super();
		this.tipo = tipo;
		this.horasDiaMinimo = horasDiaMinimo;
		this.cilindrajeMaxSinRecargo = cilindrajeMaxSinRecargo;
		this.cupo = cupo;
		this.valorHora = valorHora;
		this.valorDia = valorDia;
		this.valorRecargo = valorRecargo;
	}
	
	public int getHorasDiaMinimo() {
		return horasDiaMinimo;
	}

	public int getCilindrajeMaxSinRecargo() {
		return cilindrajeMaxSinRecargo;
	}

	public int getCupo() {
		return cupo;
	}

	public double getValorHora() {
		return valorHora;
	}

	public double getValorDia() {
		return valorDia;
	}

	public double getValorRecargo() {
		return valorRecargo;
	}

	public static Conditions get(Vehicle vehicle) {
		if(vehicle.getTipo().equals(Moto.TIPO)) {
			return motoCondiciones;
		}else {
			return carroCondiciones;

		}
	}

}
