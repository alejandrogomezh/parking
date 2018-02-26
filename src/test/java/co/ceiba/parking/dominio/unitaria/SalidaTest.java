package co.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import co.ceiba.parking.services.DateServiceInterface;
import co.ceiba.parking.domain.Vigilant;
import co.ceiba.parking.domain.excepcion.ParkingException;
import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Moto;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.persistent.services.InvoiceService;
import co.ceiba.parking.persistent.services.RegisterService;
import co.ceiba.parking.persistent.services.ServicesPersistent;
import co.ceiba.parking.persistent.services.VehicleService;
import testdatabuilder.MotoTestDataBuilder;
import testdatabuilder.RegisterTestDataBuilder;
import testutilidades.FechaTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalidaTest {
	
	private DateServiceInterface dateServiceInterface;
	
	private ServicesPersistent servicesPersistent;
	private InvoiceService invoiceService;
	private RegisterService registerService;
	private VehicleService vehicleService;
	
	
	@Before
	public void setup() {
		dateServiceInterface = mock(DateServiceInterface.class);
		servicesPersistent = mock(ServicesPersistent.class);
		invoiceService = mock(InvoiceService.class);
		registerService = mock(RegisterService.class);
		vehicleService = mock(VehicleService.class);
	}
	
	@Test public void vehiculoNoExiste() {
		// arrange
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();

		when(servicesPersistent.getInvoiceService()).thenReturn(invoiceService);
		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);

		Vigilant vigilant = new Vigilant(dateServiceInterface, servicesPersistent);
		
		// act
		try {
			vigilant.output(moto);
			fail();
		} catch (ParkingException e) {
			// assert	
			assertEquals(Messages.VEHICULO_NO_EXISTE, e.getMessage());
		}

	}

	@Test public void vehiculoNoIngresado() {
		// arrange
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();

		when(servicesPersistent.getInvoiceService()).thenReturn(invoiceService);
		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);
		
		when(vehicleService.findByPlaca(moto.getPlaca())).thenReturn(moto);
		

		Vigilant vigilant = new Vigilant(dateServiceInterface, servicesPersistent);
		
		// act
		try {
			vigilant.output(moto);
			fail();
		} catch (ParkingException e) {
			// assert	
			assertEquals(Messages.NO_A_INGRESADO, e.getMessage());
		}
	}
	
	@Test public void salidaExitosa() {
		// arrange
		Date ingreso = FechaTest.crearFechaConHora(24, Calendar.MAY, 2018, 12, 0);
		Date salida = FechaTest.crearFechaConHora(25, Calendar.MAY, 2018, 15, 0);
		Moto moto = new MotoTestDataBuilder().conPlaca("ABC21G").build();
		Register register = new RegisterTestDataBuilder()
				.conIngreso(ingreso)
				.conVehiculo(moto)
				.build();

		when(servicesPersistent.getInvoiceService()).thenReturn(invoiceService);
		when(servicesPersistent.getRegisterService()).thenReturn(registerService);
		when(servicesPersistent.getVehicleService()).thenReturn(vehicleService);
		
		when(vehicleService.findByPlaca(moto.getPlaca())).thenReturn(moto);
		when(registerService.findByVehicleActive(moto)).thenReturn(register);

		when(dateServiceInterface.dateNow()).thenReturn(salida);
		
		Vigilant vigilant = new Vigilant(dateServiceInterface, servicesPersistent);
		// act
		Invoice invoice = vigilant.output(moto);
		// assert	
		assertNotNull(invoice);

	}

}
