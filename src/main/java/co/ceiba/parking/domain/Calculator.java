package co.ceiba.parking.domain;

import co.ceiba.parking.domain.interfaces.TimeInterface;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;

public class Calculator {
	private Conditions conditions;
	private Register register;
	
	public Calculator(Conditions conditions, Register register) {
		this.conditions = conditions;
		this.register = register;
	}

	public Time time() {
		long ingreso = register.getIngreso().getTime();
		long salida = register.getSalida().getTime();

		int horasDiaMinimo = conditions.getHorasDiaMinimo();
		int horasDiaMaximo = 24;
		
		long segundosTotal = (salida - ingreso) / 1000;
		int minutosTotal = (int) (segundosTotal / 60);
		int horasTotal = (minutosTotal / 60); //cuantos minutos o segundos de mas incrementa la hora
		if((minutosTotal % 60) >= 1) {
			horasTotal++; //se incrementa la hora si se pasa de un minuto ejemplo 1 minuto de parqueo equivale a 1 hora
		}
		int dias = (horasTotal / horasDiaMaximo);
		int horas = (horasTotal % horasDiaMaximo);
		if(horas >= horasDiaMinimo) {
			dias++;
			horas = 0;
		}
		
		return new Time(horas, dias);
	}
	
	public Cost cost(TimeInterface time) {
		Vehicle vehicle = register.getVehicle();
		
		double valorDias = time.getDias() * conditions.getValorDia();
		double valorHoras = time.getHoras() * conditions.getValorHora();
		
		double valorRecargo = 0;
		int cilindrajeMaxSinRecargo = conditions.getCilindrajeMaxSinRecargo();
		if((cilindrajeMaxSinRecargo >= 0) && (vehicle.getCilindraje() > cilindrajeMaxSinRecargo)) {
			valorRecargo = conditions.getValorRecargo();
		}
		
		double valorTotal = valorDias + valorHoras + valorRecargo;
		
		return new Cost(valorDias, valorHoras, valorRecargo, valorTotal);
	}
	
}
