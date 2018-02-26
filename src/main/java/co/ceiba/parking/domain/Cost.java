package co.ceiba.parking.domain;

import co.ceiba.parking.domain.interfaces.CostInterface;

public class Cost implements CostInterface{
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;

	

	public Cost(double valorDias, double valorHoras, double valorRecargo, double valorTotal) {
		this.valorDias = valorDias;
		this.valorHoras = valorHoras;
		this.valorRecargo = valorRecargo;
		this.valorTotal = valorTotal;
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
