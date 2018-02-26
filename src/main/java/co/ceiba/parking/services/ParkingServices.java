package co.ceiba.parking.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingServices implements DateServiceInterface {
	private DateServiceInterface dateServiceInterface;
	
	@Autowired
	public ParkingServices(DateService parkingService) {
		this.dateServiceInterface = parkingService;
	}
	
	public Date dateNow() {
		return dateServiceInterface.dateNow();
	}
	
}
