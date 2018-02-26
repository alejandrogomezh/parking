package co.ceiba.parking.domain;

import co.ceiba.parking.domain.interfaces.TimeInterface;

public class Time implements TimeInterface{
	private int horas;
	private int dias;
	
	

	public Time(int horas, int dias) {
		this.horas = horas;
		this.dias = dias;
	}

	public int getHoras() {
		return horas;
	}

	public int getDias() {
		return dias;
	}

}
