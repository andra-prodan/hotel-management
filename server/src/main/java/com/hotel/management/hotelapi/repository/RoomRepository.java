package com.hotel.management.hotelapi.repository;

import com.hotel.management.hotelapi.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    List<RoomEntity> findByHotelId(int hotelId);
}
