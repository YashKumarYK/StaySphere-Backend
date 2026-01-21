package com.yash.project.HotelManagementApp.controller;

import com.yash.project.HotelManagementApp.dto.HotelDto;
import com.yash.project.HotelManagementApp.dto.HotelInfoDto;
import com.yash.project.HotelManagementApp.dto.HotelPriceDto;
import com.yash.project.HotelManagementApp.dto.HotelSearchRequest;
import com.yash.project.HotelManagementApp.service.HotelService;
import com.yash.project.HotelManagementApp.service.InventoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotel(@RequestBody HotelSearchRequest hotelSearchRequest){

        Page<HotelPriceDto> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        HotelInfoDto  hotelInfo= hotelService.getHotelInfoById(hotelId);
        return ResponseEntity.ok(hotelInfo);
    }
}

