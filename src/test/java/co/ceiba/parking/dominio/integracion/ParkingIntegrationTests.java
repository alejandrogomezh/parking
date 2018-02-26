package co.ceiba.parking.dominio.integracion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.ceiba.parking.domain.Conditions;
import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.domain.objects.Register;
import co.ceiba.parking.domain.objects.Moto;
import co.ceiba.parking.messages.Messages;
import co.ceiba.parking.services.ParkingServices;
import testutilidades.FechaTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    private ParkingServices parkingServices;

    @Test
    @Sql({"drop_tables.sql", "create_tables.sql"})
    public void InputTypeEmpty() throws Exception {
    	Date input =  FechaTest.crearFechaConHora(18, Calendar.FEBRUARY, 2018, 15, 0); // SunFeb18De2018A15pm 
    	when(parkingServices.dateNow()).thenReturn(input);
    	
    	
    	MockHttpServletRequestBuilder builder = put("/input")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"tipo\":\"\", \"placa\":\"ASK43B\", \"cilindraje\":\"150\"}");

    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    
	    assertEquals(Messages.DEBE_INGRESAR_TIPO_VEHICULO, msg.getMsg());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql"})
    public void InputPlateEmpty() throws Exception {
    	Date input =  FechaTest.crearFechaConHora(18, Calendar.FEBRUARY, 2018, 15, 0); // SunFeb18De2018A15pm 
    	when(parkingServices.dateNow()).thenReturn(input);
    	
    	MockHttpServletRequestBuilder builder = put("/input")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"tipo\":\"moto\", \"placa\":\"\", \"cilindraje\":\"150\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    
	    assertEquals(Messages.DEBE_INGRESAR_PLACA, msg.getMsg());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql"})
    public void errorInputMotoADaySun() throws Exception {
    	Date input =  FechaTest.crearFechaConHora(18, Calendar.FEBRUARY, 2018, 15, 0); // SunFeb18De2018A15pm 
    	when(parkingServices.dateNow()).thenReturn(input);
    	
    	MockHttpServletRequestBuilder builder = put("/input")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"tipo\":\"moto\", \"placa\":\"ASK43B\", \"cilindraje\":\"150\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    
	    assertEquals(Messages.INGRESO_NO_AUTORIZADO, msg.getMsg());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "reg_20Car_10Moto.sql"})
    public void errorInputMotoWithoutSpace() throws Exception {
    	Date input =  FechaTest.crearFechaConHora(18, Calendar.FEBRUARY, 2018, 15, 0); // SunFeb18De2018A15pm 
    	when(parkingServices.dateNow()).thenReturn(input);
    	
    	MockHttpServletRequestBuilder builder = put("/input")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"tipo\":\"moto\", \"placa\":\"PTT123\", \"cilindraje\":\"150\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    
	    assertEquals(Messages.NO_HAY_CUPO, msg.getMsg());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "reg_20Car_10Moto.sql"})
    public void errorInputCarWithoutSpace() throws Exception {
    	Date input =  FechaTest.crearFechaConHora(18, Calendar.FEBRUARY, 2018, 15, 0); // SunFeb18De2018A15pm 
    	when(parkingServices.dateNow()).thenReturn(input);
    	
    	MockHttpServletRequestBuilder builder = put("/input")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"tipo\":\"carro\", \"placa\":\"PTT123\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    
	    assertEquals(Messages.NO_HAY_CUPO, msg.getMsg());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "regM_NSK43B_250CC_inMonFeb19De2018A12m.sql"})
    public void errorInputMotoExistRegister() throws Exception {
    	Date input =  FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // MonFeb19sDe2018 
    	when(parkingServices.dateNow()).thenReturn(input);
    	
    	MockHttpServletRequestBuilder builder = put("/input")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"tipo\":\"moto\", \"placa\":\"NSK43B\", \"cilindraje\":\"150\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    
	    assertEquals(Messages.YA_HABIA_INGRESADO, msg.getMsg());
    }

    @Test
    @Sql({"drop_tables.sql", "create_tables.sql"})
    public void successInputMoto() throws Exception {
    	Date input =  FechaTest.crearFecha(19, Calendar.FEBRUARY, 2018); // MonFeb19sDe2018 
    	when(parkingServices.dateNow()).thenReturn(input);
    	
    	MockHttpServletRequestBuilder builder = put("/input")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"tipo\":\"moto\", \"placa\":\"ASK43B\", \"cilindraje\":\"150\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    
	    assertEquals(Messages.INGRESO_SATISFACTORIO, msg.getMsg());
    }

    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "regM_NSK43B_250CC_inMonFeb19De2018A12m.sql"})
    public void OutputMoto1Day3Hours() throws Exception {
    	//input Lunes Febrero 19 de 2018, 12m
    	Date output =  FechaTest.crearFechaConHora(20, Calendar.FEBRUARY, 2018, 15, 0); // TueFeb20De2018A15pm 
    	when(parkingServices.dateNow()).thenReturn(output);
    	
    	MockHttpServletRequestBuilder builder = put("/output")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"placa\":\"NSK43B\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Invoice invoice = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Invoice>(){}
	    		);
	    
	    Conditions conditions = Conditions.get(new Moto("NSK43B", 250));
	    
			double valorDia = conditions.getValorDia()*1;
			double valorHora = conditions.getValorHora()*3;
			double valorTotal = valorDia + valorHora;

	    
	    assertEquals(1, invoice.getDias());
	    assertEquals(3, invoice.getHoras());
	    assertEquals(valorTotal, invoice.getValorTotal(), 0);
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql"})
    public void OutputMotoNoExist() throws Exception {
    	when(parkingServices.dateNow()).thenReturn(new Date());
    	
    	MockHttpServletRequestBuilder builder = put("/output")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"placa\":\"PTR53D\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    	    
	    assertEquals(Messages.VEHICULO_NO_EXISTE, msg.getMsg());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "vehM_NSK43B_250CC.sql"})
    public void OutputMotoNoInputWithoutRegister() throws Exception {
    	when(parkingServices.dateNow()).thenReturn(new Date());
    	
    	MockHttpServletRequestBuilder builder = put("/output")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"placa\":\"NSK43B\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    	    
	    assertEquals(Messages.NO_A_INGRESADO, msg.getMsg());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "regM_NSK43B_250CC_in_out.sql"})
    public void OutputMotoNoInputWithRegisterInOut() throws Exception {
    	when(parkingServices.dateNow()).thenReturn(new Date());
    	
    	MockHttpServletRequestBuilder builder = put("/output")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"placa\":\"NSK43B\"}");
    	
	    MvcResult mvcResult = this.mockMvc.perform(builder)
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    Messages msg = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<Messages>(){}
	    		);
	    	    
	    assertEquals(Messages.NO_A_INGRESADO, msg.getMsg());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "reg_9in_1out.sql"})
    public void registerGet9Input() throws Exception {
	    MvcResult mvcResult = this.mockMvc.perform(
	    		get("/registerFilter")
	            .contentType(MediaType.APPLICATION_JSON)
	            .param("filter", "In"))
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    List<Register> registers = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<List<Register>>(){}
	    		);
	    
	    
	    assertEquals(9, registers.size());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "reg_9in_1out.sql"})
    public void registerGet1Output() throws Exception {
	    MvcResult mvcResult = this.mockMvc.perform(
	    		get("/registerFilter")
	            .contentType(MediaType.APPLICATION_JSON)
	            .param("filter", "Out"))
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    List<Register> registers = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<List<Register>>(){}
	    		);
	    
	    
	    assertEquals(1, registers.size());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "reg_9in_1out.sql"})
    public void registerGet10All() throws Exception {
	    MvcResult mvcResult = this.mockMvc.perform(
	    		get("/registerFilter")
	            .contentType(MediaType.APPLICATION_JSON)
	            .param("filter", "All"))
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    List<Register> registers = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<List<Register>>(){}
	    		);
	    
	    
	    assertEquals(10, registers.size());
    }
    
    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "reg_9in_1out.sql"})
    public void registerGet0Default() throws Exception {
	    MvcResult mvcResult = this.mockMvc.perform(
	    		get("/registerFilter")
	            .contentType(MediaType.APPLICATION_JSON)
	            .param("filter", ""))
	      .andExpect(status().isOk())
	      .andReturn();
	    
	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
	    
	    List<Register> registers = this.objectMapper.readValue(
	    		response,
	    		new TypeReference<List<Register>>(){}
	    		);
	    
	    
	    assertEquals(0, registers.size());
    }
    
}