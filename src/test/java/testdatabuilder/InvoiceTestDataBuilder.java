package testdatabuilder;

import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Register;

public class InvoiceTestDataBuilder {
	private static final int DIAS = 1;
	private static final int HORAS = 3;
	private static final double VALOR_DIA = 4000;
	private static final double VALOR_HORAS = 1500;
	private static final double VALOR_RECARGO = 2000;
	private static final double VALOR_TOTAL = 6500;

	
	private Register register;
	private int dias;
	private int horas;
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;
	
	public InvoiceTestDataBuilder() {
		this.register = new RegisterTestDataBuilder().build();
		this.dias = DIAS;
		this.horas = HORAS;
		this.valorDias = VALOR_DIA;
		this.valorHoras = VALOR_HORAS;
		this.valorRecargo = VALOR_RECARGO;
		this.valorTotal = VALOR_TOTAL;
	}

	public InvoiceTestDataBuilder conRegistro(Register register) {
		this.register = register;
		return this;
	}

	public InvoiceTestDataBuilder conDias(int dias) {
		this.dias = dias;
		return this;
	}

	public InvoiceTestDataBuilder conHoras(int horas) {
		this.horas = horas;
		return this;
	}

	public InvoiceTestDataBuilder conValorDias(double valorDias) {
		this.valorDias = valorDias;
		return this;
	}

	public InvoiceTestDataBuilder conValorHoras(double valorHoras) {
		this.valorHoras = valorHoras;
		return this;
	}

	public InvoiceTestDataBuilder conValorRecargo(double valorRecargo) {
		this.valorRecargo = valorRecargo;
		return this;
	}

	public InvoiceTestDataBuilder conValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}

	public Invoice  build() {
		return new Invoice(
			register,
			dias,
			horas,
			valorDias,
			valorHoras,
			valorRecargo,
			valorTotal
		);
	}
}
