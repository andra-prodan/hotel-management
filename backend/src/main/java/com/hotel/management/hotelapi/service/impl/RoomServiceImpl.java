package com.hotel.management.hotelapi.service.impl;

import com.hotel.management.hotelapi.dto.RoomDto;
import com.hotel.management.hotelapi.exceptions.HotelNotFoundException;
import com.hotel.management.hotelapi.exceptions.RoomNotFoundException;
import com.hotel.management.hotelapi.mappers.RoomMapper;
import com.hotel.management.hotelapi.models.HotelEntity;
import com.hotel.management.hotelapi.models.RoomEntity;
import com.hotel.management.hotelapi.repository.HotelRepository;
import com.hotel.management.hotelapi.repository.RoomRepository;
import com.hotel.management.hotelapi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<RoomDto> getAllRooms(){
        List<RoomEntity> roomEntities = roomRepository.findAll();

        return roomEntities.stream().map(RoomMapper::mapRoomToDto).collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(int id) {
        return RoomMapper.mapRoomToDto(roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room associated with this id " + id + " was not found!")));
    }

    @Override
    public List<RoomDto> getRoomsByHotelId(int hotelId, boolean isAvailable) {
        try {
            List<RoomEntity> roomEntities = roomRepository.findByHotelId(hotelId);

            if (isAvailable) {
                roomEntities = roomEntities.stream()
                        .filter(RoomEntity::isAvailable)
                        .toList();
            }

            return roomEntities.stream()
                    .map(RoomMapper::mapRoomToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new HotelNotFoundException(e.getMessage());
        }
    }

    @Override
    public RoomDto createRoom(int hotelId,RoomDto roomDto) {
        HotelEntity hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel associated with this id was not found!"));

        RoomEntity roomEntity = RoomMapper.mapRoomToEntity(roomDto, hotel);

        return RoomMapper.mapRoomToDto(roomRepository.save(roomEntity));
    }

    @Override
    public RoomDto updateRoom(int id, RoomDto roomDto) {
        RoomEntity roomEntity = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room associated with this id " + id + " was not found!"));
        HotelEntity hotel = hotelRepository.findById(roomEntity.getHotel().getId()).orElseThrow(() -> new HotelNotFoundException("Hotel associated with this id " + roomDto.getHotelId() + " was not found!"));

        RoomEntity room = RoomMapper.mapRoomToEntity(getRoomById(id), hotel);

        room.setRoomNumber(roomDto.getRoomNumber());
        room.setType(roomDto.getType());
        room.setPrice(roomDto.getPrice());
        room.setAvailable(roomDto.isAvailable());

        roomRepository.save(room);

        return RoomMapper.mapRoomToDto(room);
    }

    @Override
    public HttpStatus deleteRoom(int id){
        roomRepository.deleteById(id);

        return HttpStatus.OK;
    }

}
