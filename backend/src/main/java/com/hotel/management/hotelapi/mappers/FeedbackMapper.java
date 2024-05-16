package com.hotel.management.hotelapi.mappers;

import com.hotel.management.hotelapi.dto.FeedbackDto;
import com.hotel.management.hotelapi.models.FeedbackEntity;
import com.hotel.management.hotelapi.models.HotelEntity;

public class FeedbackMapper {
    public static FeedbackDto mapFeedbackToDto(FeedbackEntity feedbackEntity){
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedbackEntity.getId());
        feedbackDto.setMessage(feedbackEntity.getMessage());
        feedbackDto.setHotelId(feedbackEntity.getHotel().getId());
        return feedbackDto;
    }

    public static FeedbackEntity mapFeedbackToEntity(FeedbackDto feedbackDto, HotelEntity hotel){
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setMessage(feedbackDto.getMessage());
        feedbackEntity.setHotel(hotel);
        return feedbackEntity;
    }
}
