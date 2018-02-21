package co.ceiba.parking;

import co.ceiba.parking.security.model.Role;
import co.ceiba.parking.security.model.User;
import co.ceiba.parking.security.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingApplication {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  
  @Autowired
  private UserRepository userRepository;
  
  @PostConstruct
  public void init(){
      User user = new User(
              "Memory",
              "Not Found",
              "info@memorynotfound.com",
              passwordEncoder.encode("password"),
              Arrays.asList(
                      new Role("ROLE_USER"),
                      new Role("ROLE_ADMIN")));

      if (userRepository.findByEmail(user.getEmail()) == null){
          userRepository.save(user);
      }
  }

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}
}
