package com.hotel.management.hotelapi.service.impl;

import com.hotel.management.hotelapi.dto.ReservationDto;
import com.hotel.management.hotelapi.exceptions.ReservationNotFoundException;
import com.hotel.management.hotelapi.mappers.ReservationMapper;
import com.hotel.management.hotelapi.models.ReservationEntity;
import com.hotel.management.hotelapi.models.RoomEntity;
import com.hotel.management.hotelapi.repository.ReservationRepository;
import com.hotel.management.hotelapi.repository.RoomRepository;
import com.hotel.management.hotelapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

   @Override
    public List<ReservationDto> getAllReservations(){
        List<ReservationEntity> reservationEntities = reservationRepository.findAll();

        return reservationEntities.stream().map(ReservationMapper::mapReservationToDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto getReservationById(int id) {
        return ReservationMapper.mapReservationToDto(reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation associated with this id " + id + " was not found!")));
    }

    @Override
    public ReservationDto createReservation(int roomId, ReservationDto reservationDto) {
        RoomEntity room = roomRepository.findById(roomId).orElseThrow(() -> new ReservationNotFoundException("Room associated with this id was not found!"));

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckIn(LocalDateTime.parse(reservationDto.getCheckIn() + "T16:00:00"));
        reservationEntity.setCheckOut(LocalDateTime.parse(reservationDto.getCheckOut() + "T12:00:00"));
        reservationEntity.setRoom(room);

        room.setAvailable(false);

        roomRepository.save(room);
        reservationRepository.save(reservationEntity);

        return ReservationMapper.mapReservationToDto(reservationEntity);
    }

    @Override
    public ReservationDto updateReservation(int id, ReservationDto reservationDto){
        ReservationDto currentReservation = getReservationById(id);

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime checkInDate = LocalDateTime.parse(currentReservation.getCheckIn());

        long hoursDifference = ChronoUnit.HOURS.between(currentDate, checkInDate);

        if(hoursDifference >= 2){
        ReservationEntity newReservation = new ReservationEntity();
        RoomEntity room = roomRepository.findById(reservationDto.getRoomId()).orElseThrow(() -> new ReservationNotFoundException("Room associated with this id was not found!"));

        newReservation.setId(id);
        newReservation.setCheckIn(LocalDateTime.parse(currentReservation.getCheckIn()));
        newReservation.setCheckOut(LocalDateTime.parse(currentReservation.getCheckOut()));


        newReservation.setRoom(room);

        reservationRepository.save(newReservation);

        return reservationDto;
        }

        return null;
    }

    @Override
    public HttpStatus deleteReservation(int id){
        ReservationDto currentReservation = getReservationById(id);

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime checkInDate = LocalDateTime.parse(currentReservation.getCheckIn());

        long hoursDifference = ChronoUnit.HOURS.between(currentDate, checkInDate);

        if(hoursDifference >= 2) {
            reservationRepository.deleteById(id);

            return HttpStatus.OK;
        }

        return HttpStatus.BAD_REQUEST;
    }
}
