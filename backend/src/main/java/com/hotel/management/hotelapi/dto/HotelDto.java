package com.hotel.management.hotelapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelDto {
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private List<RoomDto> rooms;
}
