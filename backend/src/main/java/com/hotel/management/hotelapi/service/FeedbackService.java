package com.hotel.management.hotelapi.service;

import com.hotel.management.hotelapi.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    List<FeedbackDto> getFeedbackByHotel(int hotelId);
    FeedbackDto createFeedback(int hotelId ,FeedbackDto feedbackDto);
}
