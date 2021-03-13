package com.oquintero.reservations.api.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/***
 * Model class to represent a reservation.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */

@Entity
public class Reservation {

	public Reservation() {
	}

	public Reservation(long id, String name, Date time) {
		this.id = id;
		this.name = name;
		this.time = time;
	}

	/**
	 * ID Number of reservation
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue
	private long id;

	/**
	 * Name of reservation
	 */
	@Getter
	@Setter
	private String name;

	/**
	 * Date of reservation
	 */
	@Getter
	@Setter
	private Date time;
}
