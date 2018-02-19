package co.ceiba.parking.persistencia.entidad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name="registroingreso")

@NamedQueries({
	@NamedQuery(name = "RegistroIngreso.cantidadPorTipoVehiculo", query = "SELECT COUNT(*) from registroingreso where registroingreso.tipo = :tipo"),
	@NamedQuery(name = "RegistroIngreso.obtenerPorPlaca", query = "SELECT from registroingreso where registroingreso.vehiculo.placa = :placa")
}) 
public class RegistroIngresoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private VehiculoEntity vehiculo;
	private Date ingreso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Date getIngreso() {
		return ingreso;
	}
	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}
}
