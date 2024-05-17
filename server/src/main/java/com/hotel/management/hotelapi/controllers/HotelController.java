package com.hotel.management.hotelapi.controllers;

import com.hotel.management.hotelapi.dto.HotelDto;
import com.hotel.management.hotelapi.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels/")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("")
    public ResponseEntity<List<HotelDto>> getHotels(){
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable int id){
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    @GetMapping("nearby")
    public ResponseEntity<List<HotelDto>> getHotelsNearby(@RequestParam double latitude, @RequestParam double longitude, @RequestParam int userDistanceInKm){
        return new ResponseEntity<>(hotelService.getHotelsNearby(latitude, longitude, userDistanceInKm), HttpStatus.OK);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto){
        return new ResponseEntity<>(hotelService.createHotel(hotelDto),HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable int id, @RequestBody HotelDto hotelDto){
        return new ResponseEntity<>(hotelService.updateHotel(id,hotelDto), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public HttpStatus deleteHotel(@PathVariable int id){
        return hotelService.deleteHotel(id);
    }
}
