package co.ceiba.parking.dominio.interfaces;

import co.ceiba.parking.dominio.Condiciones;
import co.ceiba.parking.persistencia.entidad.Registro;

public interface TiemposInterface {
	Condiciones getCondicion();
	Registro getRegistro();
	int getHoras();
	int getDias();
}
