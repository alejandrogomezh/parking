package testdatabuilder;

import co.ceiba.parking.persistencia.entidad.Factura;
import co.ceiba.parking.persistencia.entidad.Registro;

public class FacturaTestDataBuilder {
	private static final int DIAS = 1;
	private static final int HORAS = 3;
	private static final double VALOR_DIA = 4000;
	private static final double VALOR_HORAS = 1500;
	private static final double VALOR_RECARGO = 2000;
	private static final double VALOR_TOTAL = 6500;

	
	private Registro registro;
	private int dias;
	private int horas;
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;
	
	public FacturaTestDataBuilder() {
		this.registro = new RegistroTestDataBuilder().build();
		this.dias = DIAS;
		this.horas = HORAS;
		this.valorDias = VALOR_DIA;
		this.valorHoras = VALOR_HORAS;
		this.valorRecargo = VALOR_RECARGO;
		this.valorTotal = VALOR_TOTAL;
	}

	public FacturaTestDataBuilder conRegistro(Registro registro) {
		this.registro = registro;
		return this;
	}

	public FacturaTestDataBuilder conDias(int dias) {
		this.dias = dias;
		return this;
	}

	public FacturaTestDataBuilder conHoras(int horas) {
		this.horas = horas;
		return this;
	}

	public FacturaTestDataBuilder conValorDias(double valorDias) {
		this.valorDias = valorDias;
		return this;
	}

	public FacturaTestDataBuilder conValorHoras(double valorHoras) {
		this.valorHoras = valorHoras;
		return this;
	}

	public FacturaTestDataBuilder conValorRecargo(double valorRecargo) {
		this.valorRecargo = valorRecargo;
		return this;
	}

	public FacturaTestDataBuilder conValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}

	public Factura  build() {
		Factura factura = new Factura();
		factura.setRegistro(registro);
		factura.setDias(dias);
		factura.setHoras(horas);
		factura.setValorDias(valorDias);
		factura.setValorHoras(valorHoras);
		factura.setValorRecargo(valorRecargo);
		factura.setValorTotal(valorTotal);
		return factura;
	}
}
