package com.yash.project.HotelManagementApp.controller;

import com.yash.project.HotelManagementApp.dto.BookingDto;
import com.yash.project.HotelManagementApp.dto.HotelDto;
import com.yash.project.HotelManagementApp.dto.HotelReportDto;
import com.yash.project.HotelManagementApp.service.BookingService;
import com.yash.project.HotelManagementApp.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;
    private final BookingService bookingService;

    @PostMapping
    @Operation(summary = "Create a new hotel", tags = {"Admin Hotel"})
    public ResponseEntity<HotelDto> createNewHotel(@Valid @RequestBody HotelDto hotelDto){
        log.info("Attempting to create a new Hotel with the name: "+hotelDto.getName());
        HotelDto hotel = hotelService.createNewHotel(hotelDto);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    // ...existing code...

    @PutMapping("/{hotelId}")
    @Operation(summary = "Update a hotel", tags = {"Admin Hotel"})
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable long hotelId, @Valid @RequestBody HotelDto hotelDto){
        HotelDto hotel = hotelService.updateHotelById(hotelId, hotelDto);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{hotelId}")
    @Operation(summary = "Delete a hotel", tags = {"Admin Hotel"})
    public ResponseEntity<Void> deleteHotelById(@PathVariable long hotelId){
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{hotelId}")
    @Operation(summary = "Activate a hotel", tags = {"Admin Hotel"})
    public ResponseEntity<Void> activateHotelById(@PathVariable long hotelId){
        hotelService.activateHotel(hotelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all hotels owned by admin", tags = {"Admin Hotel"})
    public ResponseEntity<List<HotelDto>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{hotelId}/bookings")
    @Operation(summary = "Get all bookings of a hotel", tags = {"Admin Bookings"})
    public ResponseEntity<List<BookingDto>> getAllBookingsByHotel(@PathVariable Long hotelId){
        return ResponseEntity.ok(bookingService.getAllBookingsByHotel(hotelId));
    }

    @GetMapping("/{hotelId}/reports")
    @Operation(summary = "Generate a bookings report of a hotel", tags = {"Admin Bookings"})
    public ResponseEntity<HotelReportDto> getHotelReport(@PathVariable Long hotelId,
                                                         @RequestParam(required=false)LocalDate startDate,
                                                         @RequestParam(required = false) LocalDate endDate){
        if(startDate==null) startDate = LocalDate.now().minusMonths(1);
        if(endDate==null) endDate = LocalDate.now();
        return ResponseEntity.ok(bookingService.getHotelReport(hotelId, startDate, endDate));
    }
}
