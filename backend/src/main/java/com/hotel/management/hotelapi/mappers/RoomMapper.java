package com.hotel.management.hotelapi.mappers;

import com.hotel.management.hotelapi.dto.RoomDto;
import com.hotel.management.hotelapi.models.HotelEntity;
import com.hotel.management.hotelapi.models.RoomEntity;

public class RoomMapper {
    public static RoomDto mapRoomToDto(RoomEntity roomEntity){
        RoomDto roomDto = new RoomDto();
        roomDto.setId(roomEntity.getId());
        roomDto.setRoomNumber(roomEntity.getRoomNumber());
        roomDto.setType(roomEntity.getType());
        roomDto.setPrice(roomEntity.getPrice());
        roomDto.setAvailable(roomEntity.isAvailable());
        roomDto.setHotelId(roomEntity.getHotel().getId());
        return roomDto;
    }

    public static RoomEntity mapRoomToEntity(RoomDto roomDto, HotelEntity hotel){
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(roomDto.getId());
        roomEntity.setRoomNumber(roomDto.getRoomNumber());
        roomEntity.setType(roomDto.getType());
        roomEntity.setPrice(roomDto.getPrice());
        roomEntity.setAvailable(roomDto.isAvailable());
        roomEntity.setHotel(hotel);
        return roomEntity;
    }
}
