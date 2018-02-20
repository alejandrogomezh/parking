package co.ceiba.parking.domain.interfaces;

import co.ceiba.parking.domain.Conditions;
import co.ceiba.parking.domain.objects.Register;

public interface TimeInterface {
	Conditions getCondicion();
	Register getRegister();
	int getHoras();
	int getDias();
}
