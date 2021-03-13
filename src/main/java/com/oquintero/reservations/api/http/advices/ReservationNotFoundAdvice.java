package com.oquintero.reservations.api.http.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oquintero.reservations.api.core.exceptions.ReservationNotFoundException;

/**
 * Exception used to identify when a reservation does not exists in database and 
 * when a ReservationNotFoundException was handled, this exception is used to return
 * the HTTP CODE properly as not found.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@ControllerAdvice
public class ReservationNotFoundAdvice {

	@ResponseBody
	  @ExceptionHandler(ReservationNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  public String reservationNotFoundHandler(ReservationNotFoundException ex) {
	    return ex.getMessage();
	  }
}
