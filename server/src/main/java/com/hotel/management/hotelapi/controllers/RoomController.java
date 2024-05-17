package com.hotel.management.hotelapi.controllers;

import com.hotel.management.hotelapi.dto.RoomDto;
import com.hotel.management.hotelapi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms/")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {this.roomService = roomService;}

    @GetMapping("")
    public ResponseEntity<List<RoomDto>> getRooms(){
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable int id){
        return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
    }

    @GetMapping("{hotelId}/rooms")
    public ResponseEntity<List<RoomDto>> getRoomsByHotelId(@PathVariable int hotelId, @RequestParam(required = false) boolean isAvailable){
        return new ResponseEntity<>(roomService.getRoomsByHotelId(hotelId, isAvailable), HttpStatus.OK);
    }

    @PostMapping("create/{hotelId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomDto> createRoom(@PathVariable int hotelId,@RequestBody RoomDto roomDto){
        return new ResponseEntity<>(roomService.createRoom(hotelId, roomDto),HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable int id,@RequestBody RoomDto roomDto){
        return new ResponseEntity<>(roomService.updateRoom(id, roomDto), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public HttpStatus deleteRoom(@PathVariable int id){
        return roomService.deleteRoom(id);
    }
}
