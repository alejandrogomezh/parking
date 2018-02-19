package co.ceiba.parking.dominio;

import co.ceiba.parking.dominio.interfaces.CostosInterface;
import co.ceiba.parking.dominio.interfaces.TiemposInterface;
import co.ceiba.parking.dominio.objetos.Registro;
import co.ceiba.parking.dominio.objetos.Vehiculo;

public class Costos implements CostosInterface{
	private TiemposInterface tiempos;
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;

	public Costos(TiemposInterface tiempos) {
		this.tiempos = tiempos;
	}

	public Costos calcular() {
		Condiciones condicion = tiempos.getCondicion();
		Registro registro = tiempos.getRegistro();
		Vehiculo vehiculo = registro.getVehiculo();
		
		valorDias = tiempos.getDias() * condicion.getValorDia();
		valorHoras = tiempos.getHoras() * condicion.getValorHora();
		
		valorRecargo = 0;
		int cilindrajeMaxSinRecargo = condicion.getCilindrajeMaxSinRecargo();
		if((cilindrajeMaxSinRecargo >= 0) && (vehiculo.getCilindraje() > cilindrajeMaxSinRecargo)) {
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
