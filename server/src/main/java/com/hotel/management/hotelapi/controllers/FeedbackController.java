package com.hotel.management.hotelapi.controllers;

import com.hotel.management.hotelapi.dto.FeedbackDto;
import com.hotel.management.hotelapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback/")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("{hotelId}")
    public ResponseEntity<List<FeedbackDto>> getFeedbackByHotel(@PathVariable int hotelId){
        return new ResponseEntity<>(feedbackService.getFeedbackByHotel(hotelId), HttpStatus.OK);
    }

    @PostMapping("create/{hotelId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FeedbackDto> createFeedback(@PathVariable int hotelId, @RequestBody FeedbackDto feedbackDto){
        return new ResponseEntity<>(feedbackService.createFeedback(hotelId ,feedbackDto),HttpStatus.CREATED);
    }
}
