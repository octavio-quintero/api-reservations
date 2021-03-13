package com.oquintero.reservations.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oquintero.reservations.api.models.Reservation;

/**
 * Repository interface for Reservation to make different operations in database, 
 * extends from JpaRepository to reuse the methods to save and grab the information.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

}
