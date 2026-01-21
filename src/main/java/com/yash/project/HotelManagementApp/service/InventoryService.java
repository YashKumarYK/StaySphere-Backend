package com.yash.project.HotelManagementApp.service;


import com.yash.project.HotelManagementApp.dto.HotelDto;
import com.yash.project.HotelManagementApp.dto.HotelPriceDto;
import com.yash.project.HotelManagementApp.dto.HotelSearchRequest;
import com.yash.project.HotelManagementApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService{

    void initializeRoomForYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
