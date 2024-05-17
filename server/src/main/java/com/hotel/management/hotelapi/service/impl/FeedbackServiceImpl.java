package com.hotel.management.hotelapi.service.impl;

import com.hotel.management.hotelapi.dto.FeedbackDto;
import com.hotel.management.hotelapi.exceptions.HotelNotFoundException;
import com.hotel.management.hotelapi.mappers.FeedbackMapper;
import com.hotel.management.hotelapi.models.FeedbackEntity;
import com.hotel.management.hotelapi.models.HotelEntity;
import com.hotel.management.hotelapi.repository.FeedbackRepository;
import com.hotel.management.hotelapi.repository.HotelRepository;
import com.hotel.management.hotelapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, HotelRepository hotelRepository) {
        this.feedbackRepository = feedbackRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<FeedbackDto> getFeedbackByHotel(int hotelId) {
        List<FeedbackEntity> feedbackEntities = feedbackRepository.findByHotelId(hotelId);

        return feedbackEntities.stream()
                .map(FeedbackMapper::mapFeedbackToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDto createFeedback(int hotelId ,FeedbackDto feedbackDto) {
        HotelEntity hotel = hotelRepository.findById(hotelId).orElseThrow(()->new HotelNotFoundException("Hotel associated with this id was not found!"));

        return FeedbackMapper.mapFeedbackToDto(feedbackRepository.save(FeedbackMapper.mapFeedbackToEntity(feedbackDto, hotel)));
    }
}
