package com.hotel.management.hotelapi.service;

import com.hotel.management.hotelapi.dto.ReservationDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> getAllReservations();
    ReservationDto getReservationById(int id);
    ReservationDto createReservation(int roomId, ReservationDto reservationDto);
    ReservationDto updateReservation(int id, ReservationDto reservationDto);
    HttpStatus deleteReservation(int id);
}
