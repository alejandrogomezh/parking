package co.ceiba.parking.persistencia.constructor;

import co.ceiba.parking.dominio.RegistroIngreso;
import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.persistencia.entidad.RegistroIngresoEntity;
import co.ceiba.parking.persistencia.entidad.VehiculoEntity;

public class RegistroIngresoConstructor {

	private RegistroIngresoConstructor() {
		
	}
	
	public static RegistroIngreso aDominio(RegistroIngresoEntity registroIngresoEntity) {
		RegistroIngreso registroIngreso = null;
		if(registroIngresoEntity != null) {
			Vehiculo vehiculo = VehiculoConstructor.aDominio(registroIngresoEntity.getVehiculo());
			registroIngreso = new RegistroIngreso(
					vehiculo,
					registroIngresoEntity.getIngreso()
					);
			registroIngreso.setSelfEntity(registroIngresoEntity);
		}
		return registroIngreso;
	}

	public static RegistroIngresoEntity aEntidad(RegistroIngreso registroIngreso) {
		VehiculoEntity vehiculo = registroIngreso.getVehiculo().getSelfEntity();
		RegistroIngresoEntity registroIngresoEntity = new RegistroIngresoEntity();
		registroIngresoEntity.setVehiculo(vehiculo);
		registroIngresoEntity.setIngreso(registroIngreso.getIngreso());
		return registroIngresoEntity;
	}
}
