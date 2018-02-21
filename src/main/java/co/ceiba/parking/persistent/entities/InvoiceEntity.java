package co.ceiba.parking.persistent.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="invoice")
public class InvoiceEntity implements Serializable {
	private static final long serialVersionUID = -7357939953977283108L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="register")
	private RegisterEntity register;
	private int dias;
	private int horas;
	private double valorDias;
	private double valorHoras;
	private double valorRecargo;
	private double valorTotal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RegisterEntity getRegister() {
		return register;
	}
	public void setRegister(RegisterEntity register) {
		this.register = register;
	}
	public int getDias() {
		return dias;
	}
	public void setDias(int dias) {
		this.dias = dias;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public double getValorDias() {
		return valorDias;
	}
	public void setValorDias(double valorDias) {
		this.valorDias = valorDias;
	}
	public double getValorHoras() {
		return valorHoras;
	}
	public void setValorHoras(double valorHoras) {
		this.valorHoras = valorHoras;
	}
	public double getValorRecargo() {
		return valorRecargo;
	}
	public void setValorRecargo(double valorRecargo) {
		this.valorRecargo = valorRecargo;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	@Override
	public String toString() {
		return "InvoiceEntity [id=" + id + ", register=" + register + ", dias=" + dias + ", horas=" + horas + ", valorDias="
				+ valorDias + ", valorHoras=" + valorHoras + ", valorRecargo=" + valorRecargo + ", valorTotal=" + valorTotal
				+ "]";
	}
	
}
