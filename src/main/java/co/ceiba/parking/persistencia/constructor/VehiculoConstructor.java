package co.ceiba.parking.persistencia.constructor;

import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.persistencia.entidad.VehiculoEntity;

public class VehiculoConstructor {
	
	private VehiculoConstructor() {
		
	}
	
	public static Vehiculo aDominio(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo = null;
		if(vehiculoEntity != null) {
			vehiculo = new Vehiculo(
					vehiculoEntity.getTipo(),
					vehiculoEntity.getPlaca(),
					vehiculoEntity.getCilindraje()
					);
			vehiculo.setSelfEntity(vehiculoEntity);
		}
		return vehiculo;
	}
	
	public static VehiculoEntity aEntidad(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = null;
		vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setTipo(vehiculo.getTipo());
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		vehiculoEntity.setCilindraje(vehiculo.getCilindraje());
		return vehiculoEntity;
	}
	
}
