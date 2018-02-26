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

import co.ceiba.parking.domain.objects.Invoice;
import co.ceiba.parking.service.impl.ParkingServicesInjection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
    private ParkingServicesInjection parkingServicesInjection;
 

    @Test
    @Sql({"drop_tables.sql", "create_tables.sql", "OutputVehicle.sql"})
    public void OutputVehicle() throws Exception {

    	when(parkingServicesInjection.dateNow()).thenReturn(new Date());
    	
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
	    
	
	    assertEquals(12000, invoice.getValorTotal(), 0);
    }
    
//    @Test
//    @Sql({"RegisterFilter.sql"})
//    public void registerFilter() throws Exception {
//	    MvcResult mvcResult = this.mockMvc.perform(
//	    		get("/registerFilter")
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .param("filter", "In"))
//	      .andExpect(status().isOk())
//	      .andReturn();
//	    
//	    String responseStr = mvcResult.getResponse().getContentAsString();
//	    byte[] response = mvcResult.getResponse().getContentAsByteArray();
//	    
//	    List<Register> registers = this.objectMapper.readValue(
//	    		response,
//	    		new TypeReference<List<Register>>(){}
//	    		);
//	    
//	    
//	    assertEquals(9, registers.size());
//	
//	    //Register register = registers.get(0);
//	    //assertEquals(new Date(), register.getIngreso());
//    }
    
}