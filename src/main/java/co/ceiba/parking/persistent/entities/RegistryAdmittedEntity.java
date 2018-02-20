package co.ceiba.parking.persistent.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="registryadmitted")
public class RegistryAdmittedEntity implements Serializable {	
	private static final long serialVersionUID = 1071247085388839007L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="vehicle", referencedColumnName="id")
	private VehicleEntity vehicle;
	private Date ingreso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VehicleEntity getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}
	public Date getIngreso() {
		return ingreso;
	}
	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}
}
