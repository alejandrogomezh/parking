package co.ceiba.parking.services;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateService implements DateServiceInterface{
	public Date dateNow() {
		return new Date();
	}
}
