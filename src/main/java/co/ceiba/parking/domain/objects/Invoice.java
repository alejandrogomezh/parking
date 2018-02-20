package co.ceiba.parking.domain.objects;

import co.ceiba.parking.persistent.entities.InvoiceEntity;
import co.ceiba.parking.service.persistent.InvoiceService;

public class Invoice {
	private Register register;
	private int dias;
	private int horas;
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;
	
	public Invoice(Register register, int dias, int horas, double valorDias,
			double valorHoras, double valorRecargo, double valorTotal) {
		this.register = register;
		this.dias = dias;
		this.horas = horas;
		this.valorDias = valorDias;
		this.valorHoras = valorHoras;
		this.valorRecargo = valorRecargo;
		this.valorTotal = valorTotal;
	}
	public Register getRegister() {
		return register;
	}
	public int getDias() {
		return dias;
	}
	public int getHoras() {
		return horas;
	}
	public double getValorDias() {
		return valorDias;
	}
	public double getValorHoras() {
		return valorHoras;
	}
	public double getValorRecargo() {
		return valorRecargo;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	
	
	private InvoiceEntity selfEntity;
	public InvoiceEntity getSelfEntity() {
		return selfEntity;
	}
	public Invoice setSelfEntity(InvoiceEntity selfEntity) {
		this.selfEntity = selfEntity;
		return this;
	}
	
	public Invoice persist(InvoiceService invoiceService) {
		Invoice self = invoiceService.save(this);
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}
}
