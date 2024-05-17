package com.hotel.management.hotelapi.controllers;

import com.hotel.management.hotelapi.dto.ReservationDto;
import com.hotel.management.hotelapi.dto.RoomDto;
import com.hotel.management.hotelapi.service.ReservationService;
import com.hotel.management.hotelapi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/reservations/")
public class ReservationController {
    private final ReservationService reservationService;
    private final RoomService roomService;

    @Autowired
    public ReservationController(ReservationService reservationService, RoomService roomService){
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

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
        ReservationDto currentReservation = reservationService.getReservationById(id);

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime checkInDate = LocalDateTime.parse(currentReservation.getCheckIn());

        long hoursDifference = ChronoUnit.HOURS.between(currentDate, checkInDate);

        if(hoursDifference >= 2) {
            RoomDto newRoomEntity = roomService.getRoomById(currentReservation.getRoomId());
            newRoomEntity.setAvailable(true);
            roomService.updateRoom(currentReservation.getRoomId(), newRoomEntity);

            return reservationService.deleteReservation(id);
        }

        return HttpStatus.BAD_REQUEST;
    }
}
