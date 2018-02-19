package co.ceiba.parking.persistencia.constructor;

import co.ceiba.parking.dominio.objetos.Factura;
import co.ceiba.parking.dominio.objetos.Registro;
import co.ceiba.parking.persistencia.entidad.FacturaEntity;
import co.ceiba.parking.persistencia.entidad.RegistroEntity;

public class FacturaConstructor {

	private FacturaConstructor() {
		
	}
	
	public static Factura aDominio(FacturaEntity facturaEntity) {
		Factura factura = null;
		if(facturaEntity != null) {
			Registro registro = RegistroConstructor.aDominio(facturaEntity.getRegistro());
			factura = new Factura(
					registro,
					facturaEntity.getDias(),
					facturaEntity.getHoras(),
					facturaEntity.getValorDias(),
					facturaEntity.getValorHoras(),
					facturaEntity.getValorRecargo(),
					facturaEntity.getValorTotal()
					);
			factura.setSelfEntity(facturaEntity);
		}
		return factura;
	}
	
	public static FacturaEntity aEntidad(Factura factura) {
		RegistroEntity registroEntity = factura.getRegistro().getSelfEntity();
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setRegistro(registroEntity);
		facturaEntity.setDias(factura.getDias());
		facturaEntity.setHoras(factura.getHoras());
		facturaEntity.setValorDias(factura.getValorDias());
		facturaEntity.setValorHoras(factura.getValorHoras());
		facturaEntity.setValorRecargo(factura.getValorRecargo());
		facturaEntity.setValorTotal(factura.getValorTotal());

		return facturaEntity;
	}
	
}
