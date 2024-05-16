package com.hotel.management.hotelapi.dto;

import lombok.Data;

@Data
public class RoomDto {
    private int id;
    private int roomNumber;
    private String type;
    private int price;
    private boolean isAvailable;
    private int hotelId;
}
