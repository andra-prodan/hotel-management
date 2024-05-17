package com.hotel.management.hotelapi.dto;

import lombok.Data;

@Data
public class ReservationDto {
    private int id;
    private String provUser;
    private int roomId;
    private String checkIn;
    private String checkOut;
}
