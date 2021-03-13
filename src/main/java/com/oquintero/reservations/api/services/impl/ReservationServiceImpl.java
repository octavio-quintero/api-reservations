package com.oquintero.reservations.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oquintero.reservations.api.core.exceptions.ReservationNotFoundException;
import com.oquintero.reservations.api.models.Reservation;
import com.oquintero.reservations.api.repository.IReservationRepository;
import com.oquintero.reservations.api.services.IReservationsService;

/**
 * Service implementation to manipulate the information of reservations.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@Service
public class ReservationServiceImpl implements IReservationsService {
	
	private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	IReservationRepository repository;
	
	/**
	 * Service method used to return all reservations available in database.
	 */
	public List<Reservation> getAll() {
		List<Reservation> reservations = null;
		try {
			reservations = repository.findAll();
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		return reservations;
	}
	
	/**
	 * Service method used to find a reservation by id number.
	 * 
	 * @param id	Number id of reservation.
	 * 
	 * @return Reservation 		Object with reservation data.
	 */
	public Reservation getById(Long id) {
		Reservation reservation = null;
		try {
			reservation = repository.findById(id).orElseThrow(() -> new ReservationNotFoundException());
		}catch(ReservationNotFoundException ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		return reservation;
	}


	/**
	 * Service used to create a new reservation.
	 * 
	 * @param newReservation	Object with all information about reservation.
	 * 
	 * @return Reservation		New reservation added to database.
	 */
	public Reservation add(Reservation newReservation) {
		Reservation reservation = null;
		try {
			reservation = repository.save(newReservation);
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		return reservation;
	}

	/**
	 * Service used to update a specific reservation or add new if not exists in database.
	 * 
	 * @param id	Number id of reservation to update
	 * 
	 * @param reservationUpdated	Object with information to update in reservation
	 * 
	 * @return Reservation		Object with reservation updated
	 */
	public Reservation update(Long id, Reservation reservationUpdated) {
	  Reservation reservation = null;
	  
	  try {
		  reservation = repository.findById(id).get();
		  
		  if(reservation != null) {
			reservation.setName(reservationUpdated.getName());
			reservation.setTime(reservationUpdated.getTime());
			reservation = repository.save(reservation);
		  }else {
			reservationUpdated.setId(id);
			reservation = repository.save(reservationUpdated);
		  }
		  
	  }catch(Exception ex) {
		  log.error(this.getClass().getName(), ex);
		  throw ex;
	  }
	  
	  return reservation;
	}

	
	/**
	 * Method used to delete a reservation.
	 * 
	 * @param id	Number id of reservation to delete.
	 * 
	 * @return	None
	 */
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		
	}
	
	
}
