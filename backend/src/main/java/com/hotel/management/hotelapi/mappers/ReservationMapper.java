package com.hotel.management.hotelapi.mappers;

import com.hotel.management.hotelapi.dto.ReservationDto;
import com.hotel.management.hotelapi.models.ReservationEntity;

public class ReservationMapper {
    public static ReservationDto mapReservationToDto(ReservationEntity reservationEntity){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservationEntity.getId());
        reservationDto.setCheckIn(reservationEntity.getCheckIn().toString());
        reservationDto.setCheckOut(reservationEntity.getCheckOut().toString());
        if(reservationEntity.getRoom() != null){
            reservationDto.setRoomId(reservationEntity.getRoom().getId());
        }
        return reservationDto;
    }
}
