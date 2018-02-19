package co.ceiba.parking.persistencia.constructor;

import co.ceiba.parking.dominio.objetos.Registro;
import co.ceiba.parking.dominio.objetos.Vehiculo;
import co.ceiba.parking.persistencia.entidad.RegistroEntity;
import co.ceiba.parking.persistencia.entidad.VehiculoEntity;

public class RegistroConstructor {

	private RegistroConstructor() {
		
	}
	
	public static Registro aDominio(RegistroEntity registroEntity) {
		Registro registro = null;
		if(registroEntity != null) {
			Vehiculo vahiculo = VehiculoConstructor.aDominio(registroEntity.getVehiculo());
			registro = new Registro(
					vahiculo,
					registroEntity.getIngreso(),
					registroEntity.getSalida()
					);
			registro.setSelfEntity(registroEntity);
		}
		return registro;
	}
	
	public static RegistroEntity aEntidad(Registro registro) {
		VehiculoEntity vehiculo = registro.getVehiculo().getSelfEntity();
		RegistroEntity registroEntity = new RegistroEntity();
		registroEntity.setVehiculo(vehiculo);
		registroEntity.setIngreso(registro.getIngreso());
		registroEntity.setSalida(registro.getSalida());
		return registroEntity;
	}
	
}
