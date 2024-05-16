package com.hotel.management.hotelapi.repository;

import com.hotel.management.hotelapi.models.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
    List<FeedbackEntity> findByHotelId(int hotelId);
}
