package com.yash.project.HotelManagementApp.service;

import com.yash.project.HotelManagementApp.dto.HotelDto;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long Id, HotelDto hotelDto);

    void deleteHotelById(Long Id);

    void activateHotel(Long HotelId);
}
