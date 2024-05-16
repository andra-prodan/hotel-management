package com.hotel.management.hotelapi.service;

import com.hotel.management.hotelapi.dto.HotelDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface HotelService {
    List<HotelDto> getAllHotels();
    HotelDto getHotelById(int id);
    List<HotelDto> getHotelsNearby(double latitude, double longitude, int userDistanceInKm);
    HotelDto createHotel(HotelDto hotelDto);
    HotelDto updateHotel(int id, HotelDto hotelDto);
    HttpStatus deleteHotel(int id);
}
