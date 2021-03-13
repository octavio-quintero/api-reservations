package com.oquintero.reservations.api.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.oquintero.reservations.api.models.Reservation;

/**
 * Service interface to define the operations for reservations.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@Service
public interface IReservationsService {

	/**
	 * Service method used to return all reservations available in database.
	 */
	List<Reservation> getAll();
	
	/**
	 * Service method used to find a reservation by id number.
	 * 
	 * @param id	Number id of reservation.
	 * 
	 * @return Reservation 		Object with reservation data.
	 */
	Reservation getById(Long id);
	
	/**
	 * Service used to create a new reservation.
	 * 
	 * @param newReservation	Object with all information about reservation.
	 * 
	 * @return Reservation		New reservation added to database.
	 */
	Reservation add(Reservation reservation);
	
	/**
	 * Service used to update a specific reservation or add new if not exists in database.
	 * 
	 * @param id	Number id of reservation to update
	 * 
	 * @param reservationUpdated	Object with information to update in reservation
	 * 
	 * @return Reservation		Object with reservation updated
	 */
	Reservation update(Long id, Reservation reservationUpdated);
	
	/**
	 * Method used to delete a reservation.
	 * 
	 * @param id	Number id of reservation to delete.
	 * 
	 * @return	None
	 */
	void delete(Long id);
}
