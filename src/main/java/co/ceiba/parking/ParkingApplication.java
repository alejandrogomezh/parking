package co.ceiba.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.jdbc.Sql;

@SpringBootApplication
@Sql("create_schema.sql")
public class ParkingApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}
}
