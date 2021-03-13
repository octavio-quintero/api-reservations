package com.oquintero.reservations.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.oquintero.reservations.api.services.IReservationsService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ReservationsControllerTest {

	@Autowired
    IReservationsService reservationService;
	
	@Autowired
    private MockMvc mockMvc;
	
	/**
	 * Test for API function to get all reservations.
	 * 
	 * @throws Exception
	 */
    @Test
    public void findAllReservations() throws Exception{
    	mockMvc.perform(get("/reservations")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isOk())
    		      .andExpect(content()
    		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    		      .andExpect(jsonPath("$").isArray());
    }
    
    /**
	 * Test for API function to add new reservation.
	 * 
	 * @throws Exception
	 */
    @Test
    public void addReservation() throws Exception{
    	mockMvc.perform(post("/reservations")
    			  .content("{"
				          + "  \"id\": \"7\", "
				          + "  \"name\": \"New Test Reservation\", "
				          + "  \"time\": \"2021-03-12T06:57:45.961+00:00\" "
				          + "}")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isCreated())
    		      .andExpect(content()
    		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    		      .andExpect(jsonPath("$.name", equalTo("New Test Reservation")));
    }
    
    /**
	 * Test for API function to find reservation by id.
	 * 
	 * @throws Exception
	 */
    @Test
    public void findReservationById() throws Exception{ 	
    	mockMvc.perform(get("/reservations/5")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isOk())
    		      .andExpect(content()
    		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    		      .andExpect(jsonPath("$.id",equalTo(5)));
    }
    
    /**
	 * Test for API function to delete reservation.
	 * 
	 * @throws Exception
	 */
    @Test
    public void deleteReservationById() throws Exception{
    	mockMvc.perform(get("/reservations/3")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isOk());
    }
}
