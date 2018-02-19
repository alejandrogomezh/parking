package co.ceiba.parking.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="factura")
public class FacturaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="registro")
	private RegistroEntity registro;
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
	public RegistroEntity getRegistro() {
		return registro;
	}
	public void setRegistro(RegistroEntity registro) {
		this.registro = registro;
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
}
