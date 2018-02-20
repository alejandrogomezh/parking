package co.ceiba.parking.service.persistent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.persistent.repositories.RegisterRepository;

@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	RegisterRepository registerRepository;

	@Override
	public Register save(Register register) {
		// TODO Auto-generated method stub
		return null;
	}

}
