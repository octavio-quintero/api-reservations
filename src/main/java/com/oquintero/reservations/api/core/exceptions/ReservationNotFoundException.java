package com.oquintero.reservations.api.core.exceptions;

/**
 * Exception used to identify when a reservation does not exists in database.
 *  
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
public class ReservationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReservationNotFoundException(){
		super("Reservation does not exists!!!");
	}
}
