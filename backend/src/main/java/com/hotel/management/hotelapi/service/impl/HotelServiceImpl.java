package com.hotel.management.hotelapi.service.impl;

import com.hotel.management.hotelapi.dto.HotelDto;
import com.hotel.management.hotelapi.mappers.HotelMapper;
import com.hotel.management.hotelapi.models.HotelEntity;
import com.hotel.management.hotelapi.repository.HotelRepository;
import com.hotel.management.hotelapi.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hotel.management.hotelapi.utils.GeoUtils.convertDistanceToMeters;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<HotelEntity> hotelEntities = hotelRepository.findAll();

        return hotelEntities.stream().map(HotelMapper::mapHotelToDto).collect(Collectors.toList());
    }

    @Override
    public HotelDto getHotelById(int id) {
        return HotelMapper.mapHotelToDto(hotelRepository.getReferenceById(id));
    }

    @Override
    public List<HotelDto> getHotelsNearby(double latitude, double longitude, int userDistanceInKm) {
        List<HotelEntity> hotelEntities = hotelRepository.findAll();

        return hotelEntities.stream()
                .map(hotelEntity -> {
                    double distance = convertDistanceToMeters(latitude, longitude, hotelEntity.getLatitude(), hotelEntity.getLongitude());
                    if (distance <= userDistanceInKm*1000) return HotelMapper.mapHotelToDto(hotelEntity);

                    return null;
                })
                .toList().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        return HotelMapper.mapHotelToDto(hotelRepository.save(HotelMapper.mapHotelToEntity(hotelDto)));
    }

    @Override
    public HotelDto updateHotel(int id, HotelDto hotelDto){
        HotelDto hotel = getHotelById(id);

        hotel.setName(hotelDto.getName());
        hotel.setLongitude(hotelDto.getLongitude());
        hotel.setLatitude(hotelDto.getLatitude());

        hotelRepository.save(HotelMapper.mapHotelToEntity(hotel));

        return hotel;
    }

    @Override
    public HttpStatus deleteHotel(int id){
        hotelRepository.deleteById(id);

        return HttpStatus.OK;
    }
}
