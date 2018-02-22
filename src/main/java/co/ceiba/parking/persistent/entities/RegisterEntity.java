package co.ceiba.parking.persistent.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="register")
public class RegisterEntity implements Serializable {
	private static final long serialVersionUID = -26107608688647235L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name="vehicle")
	private VehicleEntity vehicle;
	private Date ingreso;
	private Date salida;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VehicleEntity getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleEntity vehicleEntity) {
		this.vehicle = vehicleEntity;
	}
	public Date getIngreso() {
		return ingreso;
	}
	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}
	public Date getSalida() {
		return salida;
	}
	public void setSalida(Date salida) {
		this.salida = salida;
	}
	@Override
	public String toString() {
		return "RegisterEntity [id=" + id + ", vehicle=" + vehicle + ", ingreso=" + ingreso + ", salida=" + salida + "]";
	}
	
}
