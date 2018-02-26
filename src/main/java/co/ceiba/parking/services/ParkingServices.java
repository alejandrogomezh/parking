package co.ceiba.parking.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingServices implements ParkingServicesInterface {
	private ParkingServicesInterface parkingServicesInterface;
	
	@Autowired
	public ParkingServices(DateService parkingService) {
		this.parkingServicesInterface = parkingService;
	}
	
	public Date dateNow() {
		return parkingServicesInterface.dateNow();
	}
	
}
