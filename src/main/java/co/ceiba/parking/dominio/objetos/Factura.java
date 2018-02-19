package co.ceiba.parking.dominio.objetos;

import co.ceiba.parking.dominio.repositorio.FacturaRepositorio;
import co.ceiba.parking.persistencia.entidad.FacturaEntity;

public class Factura {
	private Registro registro;
	private int dias;
	private int horas;
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;
	
	public Factura(Registro registro, int dias, int horas, double valorDias,
			double valorHoras, double valorRecargo, double valorTotal) {
		this.registro = registro;
		this.dias = dias;
		this.horas = horas;
		this.valorDias = valorDias;
		this.valorHoras = valorHoras;
		this.valorRecargo = valorRecargo;
		this.valorTotal = valorTotal;
	}
	public Registro getRegistro() {
		return registro;
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
	
	
	private FacturaEntity selfEntity;
	public FacturaEntity getSelfEntity() {
		return selfEntity;
	}
	public Factura setSelfEntity(FacturaEntity selfEntity) {
		this.selfEntity = selfEntity;
		return this;
	}
	
	public Factura persistente(FacturaRepositorio facturaRepositorio) {
		Factura self = facturaRepositorio.agregar(this);
		if(self != null) this.selfEntity = self.selfEntity;
		return this;
	}
}
