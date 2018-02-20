package co.ceiba.parking.service.persistent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parking.domain.objects.RegistryAdmitted;
import co.ceiba.parking.domain.objects.Vehicle;
import co.ceiba.parking.persistent.repositories.RegistryAdmittedRepository;

@Service
public class RegistryAdmittedServiceImpl implements RegistryAdmittedService {
	
	@Autowired
	RegistryAdmittedRepository registryAdmittedRepository;
	
	@Override
	public List<RegistryAdmitted> findByVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByTypeVehicle(String tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RegistryAdmitted save(RegistryAdmitted ingresar) {
		// TODO Auto-generated method stub
		return null;
	}

}
