package co.ceiba.parking.domain.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.ceiba.parking.persistent.services.InvoiceService;

@JsonInclude(Include.NON_NULL)
public class Invoice{
	
	private Register register;
	private int dias;
	private int horas;
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;
	
	public Invoice() {
		
	}
	
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
	
	@JsonIgnore
	private Object selfEntity;
	public Object getSelfEntity() {
		return selfEntity;
	}
	public Invoice setSelfEntity(Object selfEntity) {
		this.selfEntity = selfEntity;
		return this;
	}
	
	public Invoice persist(InvoiceService invoiceService) {
		Invoice self = invoiceService.save(this);
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}
	
}
