package com.hotel.management.hotelapi.repository;

import com.hotel.management.hotelapi.models.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {
}
