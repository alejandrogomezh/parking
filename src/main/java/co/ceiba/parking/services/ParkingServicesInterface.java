package co.ceiba.parking.services;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public interface ParkingServicesInterface {
	public Date dateNow();
}
