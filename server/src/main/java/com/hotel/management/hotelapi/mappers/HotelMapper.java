package com.hotel.management.hotelapi.mappers;

import com.hotel.management.hotelapi.dto.HotelDto;
import com.hotel.management.hotelapi.models.HotelEntity;

import java.util.stream.Collectors;

public class HotelMapper {
    public static HotelDto mapHotelToDto(HotelEntity hotelEntity){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotelEntity.getId());
        hotelDto.setName(hotelEntity.getName());
        hotelDto.setLatitude(hotelEntity.getLatitude());
        hotelDto.setLongitude(hotelEntity.getLongitude());
        if(hotelEntity.getRooms() != null) {
            hotelDto.setRooms(hotelEntity.getRooms().stream().map(RoomMapper::mapRoomToDto).collect(Collectors.toList()));
        } else hotelDto.setRooms(null);
        return hotelDto;
    }

    public static HotelEntity mapHotelToEntity(HotelDto hotelDto){
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setId(hotelDto.getId());
        hotelEntity.setName(hotelDto.getName());
        hotelEntity.setLatitude(hotelDto.getLatitude());
        hotelEntity.setLongitude(hotelDto.getLongitude());
        if(hotelDto.getRooms() != null) {
            hotelEntity.setRooms(hotelDto.getRooms().stream().map(room-> RoomMapper.mapRoomToEntity(room, hotelEntity)).collect(Collectors.toList()));
        } else hotelEntity.setRooms(null);
        return hotelEntity;
    }
}
