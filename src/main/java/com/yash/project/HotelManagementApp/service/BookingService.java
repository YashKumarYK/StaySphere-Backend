package com.yash.project.HotelManagementApp.service;

import com.stripe.model.Event;
import com.yash.project.HotelManagementApp.dto.BookingDto;
import com.yash.project.HotelManagementApp.dto.BookingRequest;
import com.yash.project.HotelManagementApp.dto.GuestDto;
import com.yash.project.HotelManagementApp.dto.HotelReportDto;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {
    @Nullable
    BookingDto initializeBooking(BookingRequest bookingRequest);

    @Nullable BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayment(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotel(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDto> getMyBookings();
}
