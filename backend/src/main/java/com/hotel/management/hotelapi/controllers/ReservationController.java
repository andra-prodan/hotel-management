package com.hotel.management.hotelapi.controllers;

import com.hotel.management.hotelapi.dto.ReservationDto;
import com.hotel.management.hotelapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){ this.reservationService = reservationService;}

    @GetMapping("")
    public ResponseEntity<List<ReservationDto>> getAllReservations(){
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable int id){
        return new ResponseEntity<>(reservationService.getReservationById(id), HttpStatus.OK);
    }

    @PostMapping("create/{roomId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReservationDto> createReservation(@PathVariable int roomId, @RequestBody ReservationDto reservationDto){
        return new ResponseEntity<>(reservationService.createReservation(roomId, reservationDto),HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable int id, @RequestBody ReservationDto reservationDto){
        return new ResponseEntity<>(reservationService.updateReservation(id, reservationDto), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public HttpStatus deleteReservation(@PathVariable int id){
        return reservationService.deleteReservation(id);
    }
}
