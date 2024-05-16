package com.hotel.management.hotelapi.service;

import com.hotel.management.hotelapi.dto.RoomDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface RoomService {
    List<RoomDto> getAllRooms();
    RoomDto getRoomById(int id);
    RoomDto createRoom(int hotelId,RoomDto roomDto);
    List<RoomDto> getRoomsByHotelId(int hotelId, boolean isAvailable);
    RoomDto updateRoom(int id, RoomDto roomDto);
    HttpStatus deleteRoom(int id);
}
