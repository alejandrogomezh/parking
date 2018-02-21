package co.ceiba.parking.persistent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.persistent.builder.RegisterBuilder;
import co.ceiba.parking.persistent.entities.RegisterEntity;
import co.ceiba.parking.persistent.repositories.RegisterRepository;

@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	RegisterRepository registerRepository;

	public RegisterServiceImpl() {
		
	}
	
	public RegisterServiceImpl(RegisterRepository registerRepository) {
		this.registerRepository = registerRepository;
	}
	
	@Override
	public Register save(Register register) {
		RegisterEntity registerEntity = RegisterBuilder.toEntity(register);
		registerEntity = registerRepository.save(registerEntity);
		return RegisterBuilder.toDomain(registerEntity);
	}

}
