package co.ceiba.parking.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import co.ceiba.parking.services.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService{
	public Date dateNow() {
		return new Date();
	}
}
