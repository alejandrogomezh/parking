package co.ceiba.parking.domain;

import co.ceiba.parking.domain.interfaces.TimeInterface;
import co.ceiba.parking.domain.objects.Register;

public class Time implements TimeInterface{
	private Conditions condicion;
	private Register register;

	private int horas;
	private int dias;
	
	public Time(Conditions condicion, Register register) {
		this.condicion = condicion;
		this.register = register;
	}

	public Time calculate() {
		long ingreso = register.getIngreso().getTime();
		long salida = register.getSalida().getTime();

		int horasDiaMinimo = condicion.getHorasDiaMinimo();
		int horasDiaMaximo = 24;
		
		long segundosTotal = (salida - ingreso) / 1000;
		int minutosTotal = (int) (segundosTotal / 60);
		int horasTotal = (minutosTotal / 60); //cuantos minutos o segundos de mas incrementa la hora
		if((minutosTotal % 60) >= 1) {
			horasTotal++; //se incrementa la hora si se pasa de un minuto ejemplo 1 minuto de parqueo equivale a 1 hora
		}
		dias = (horasTotal / horasDiaMaximo);
		horas = (horasTotal % horasDiaMaximo);
		if(horas >= horasDiaMinimo) {
			dias++;
			horas = 0;
		}
		return this;
	}

	public Conditions getCondicion() {
		return condicion;
	}

	public Register getRegister() {
		return register;
	}

	public int getHoras() {
		return horas;
	}

	public int getDias() {
		return dias;
	}

}
