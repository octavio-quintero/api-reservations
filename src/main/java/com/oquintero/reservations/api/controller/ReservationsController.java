package com.oquintero.reservations.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oquintero.reservations.api.core.exceptions.ReservationNotFoundException;
import com.oquintero.reservations.api.models.Reservation;
import com.oquintero.reservations.api.services.IReservationsService;

/**
 * API Controller with CRUD Operations for reservations.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 */

@RestController()
public class ReservationsController {
	private static final Logger log = LoggerFactory.getLogger(ReservationsController.class);
	
	@Autowired
	IReservationsService service;
	
	/**
	 * Method to return a list of current reservations.
	 * 
	 * @return List<Reservations> List of reservations.
	 */
	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation> > getAll(){
		List<Reservation> reservations = null;
		try {
			reservations = service.getAll();
			
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			return new ResponseEntity<List<Reservation> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
	}
	
	/**
	 * Method to create a new reservation.
	 * 
	 * @param newReservation	Object with all information about reservation.
	 * 
	 * @return Reservation		New reservation added to database.
	 */
	@PostMapping("/reservations")
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation newReservation) {
		Reservation reservation = null;
		try {
			reservation = service.add(newReservation);
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Reservation>(reservation,HttpStatus.CREATED);
		
	}
	
	/**
	 * Method used to find a reservation by id number.
	 * 
	 * @param id	Number id of reservation.
	 * 
	 * @return Reservation 		Object with reservation data.
	 */
	@GetMapping("/reservations/{id}")
	public Reservation getReservationById(@PathVariable Long id) {
		Reservation reservation = null;
		try {
		reservation = service.getById(id);
		}catch(ReservationNotFoundException ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		return reservation;
	}
	
	/**
	 * Method used to update a specific reservation or add new if not exists in database.
	 * 
	 * @param id	Number id of reservation to update
	 * 
	 * @param reservationUpdated	Object with information to update in reservation
	 * 
	 * @return Reservation		Object with reservation updated
	 */
	@PutMapping("/reservations/{id}")
	public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservationUpdated) {
		Reservation reservation = null;
		try {
			reservation = service.update(id, reservationUpdated);
		}catch(Exception ex) {
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}
	
	/**
	 * Method used to delete a reservation.
	 * 
	 * @param id	Number id of reservation to delete.
	 * 
	 * @return	None
	 */
	@DeleteMapping("/reservations/{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
		try {
			service.delete(id);
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
