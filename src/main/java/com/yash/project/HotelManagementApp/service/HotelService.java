package com.yash.project.HotelManagementApp.service;

import com.yash.project.HotelManagementApp.dto.HotelDto;
import com.yash.project.HotelManagementApp.dto.HotelInfoDto;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long Id, HotelDto hotelDto);

    void deleteHotelById(Long Id);

    void activateHotel(Long HotelId);

    public HotelInfoDto getHotelInfoById(Long HotelId);
}
