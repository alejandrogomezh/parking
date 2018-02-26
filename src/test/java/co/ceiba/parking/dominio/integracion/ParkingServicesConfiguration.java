package co.ceiba.parking.dominio.integracion;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import co.ceiba.parking.services.ParkingServices;

@Profile("test")
@Configuration
public class ParkingServicesConfiguration {
  @Bean
  @Primary
  public ParkingServices parkingServices() {
      return Mockito.mock(ParkingServices.class);
  }
}
