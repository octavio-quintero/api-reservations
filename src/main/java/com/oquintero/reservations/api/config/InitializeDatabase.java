package com.oquintero.reservations.api.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oquintero.reservations.api.models.Reservation;
import com.oquintero.reservations.api.repository.IReservationRepository;

/**
 * Class used to initialize the default values in database regarding to initial requirement.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@Configuration
public class InitializeDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(InitializeDatabase.class);
	
	/**
	 * Method to return CommandLineRunner to initialize the database during starting server.
	 * 
	 * @param reservationRepository		Repository class to save records
	 * 
	 * @return CommandLineRunner
	 */
	@Bean
	public CommandLineRunner load(IReservationRepository reservationRepository) {
		return args -> {
			log.info("Initializing database");
			log.info("Adding initial records");
			
			try {
				reservationRepository.save(new Reservation(1, "Reservation No. 1", new Date()));
				reservationRepository.save(new Reservation(2, "Reservation No. 2", new Date()));
				reservationRepository.save(new Reservation(3, "Reservation No. 3", new Date()));
				reservationRepository.save(new Reservation(4, "Reservation No. 4", new Date()));
				reservationRepository.save(new Reservation(5, "Reservation No. 5", new Date()));
				reservationRepository.save(new Reservation(6, "Reservation No. 6", new Date()));
			}catch(Exception ex) {
				log.error("Error during initialization of database. ", ex);
			}
			
			log.info("Database initialized successfully");

		};
	}

}
