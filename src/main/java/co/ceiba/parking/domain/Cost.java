package co.ceiba.parking.domain;

import co.ceiba.parking.domain.interfaces.CostInterface;
import co.ceiba.parking.domain.interfaces.TimeInterface;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Vehicle;

public class Cost implements CostInterface{
	private TimeInterface tiempos;
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;

	public Cost(TimeInterface tiempos) {
		this.tiempos = tiempos;
	}

	public Cost calculate() {
		Conditions condicion = tiempos.getCondicion();
		Register register = tiempos.getRegister();
		Vehicle vehicle = register.getVehicle();
		
		valorDias = tiempos.getDias() * condicion.getValorDia();
		valorHoras = tiempos.getHoras() * condicion.getValorHora();
		
		valorRecargo = 0;
		int cilindrajeMaxSinRecargo = condicion.getCilindrajeMaxSinRecargo();
		if((cilindrajeMaxSinRecargo >= 0) && (vehicle.getCilindraje() > cilindrajeMaxSinRecargo)) {
			valorRecargo = condicion.getValorRecargo();
		}
		
		valorTotal = valorDias + valorHoras + valorRecargo;
		return this;
	}

	@Override
	public double getValorDias() {
		return valorDias;
	}

	@Override
	public double getValorHoras() {
		return valorHoras;
	}

	@Override
	public double getValorRecargo() {
		return valorRecargo;
	}

	@Override
	public double getValorTotal() {
		return valorTotal;
	}

}
