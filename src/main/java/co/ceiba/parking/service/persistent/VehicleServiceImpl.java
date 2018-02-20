package co.ceiba.parking.service.persistent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.repositories.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;
	
	@Transactional
	public Vehicle findByTipoPlaca(String tipo, String placa) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	public Vehicle save(Vehicle vehicleEntity) {
		// TODO Auto-generated method stub
		return null;
	}
}
