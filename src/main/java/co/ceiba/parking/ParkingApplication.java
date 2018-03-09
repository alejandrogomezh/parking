package co.ceiba.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.ceiba.scala.testing.Prueba;

@SpringBootApplication
public class ParkingApplication {
	public static void main(String[] args) {
		Prueba test = new Prueba();
		String val = test.getTest("hola");
		SpringApplication.run(ParkingApplication.class, args);
	}
}
