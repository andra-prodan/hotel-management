package com.hotel.management.hotelapi.dto;

import lombok.Data;

@Data
public class FeedbackDto {
    private int id;
    private String message;
    private int hotelId;
}
