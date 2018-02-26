package co.ceiba.parking.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parking.services.ParkingService;

@Service
public class ParkingServicesInjection implements ParkingService {
	private ParkingService parkingService;
	
	@Autowired
	public ParkingServicesInjection(ParkingServiceImpl parkingService) {
		this.parkingService = parkingService;
	}
	
	public Date dateNow() {
		return parkingService.dateNow();
	}
	
}
