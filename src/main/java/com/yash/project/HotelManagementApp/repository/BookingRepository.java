package com.yash.project.HotelManagementApp.repository;

import com.yash.project.HotelManagementApp.dto.BookingDto;
import com.yash.project.HotelManagementApp.entity.Booking;
import com.yash.project.HotelManagementApp.entity.Hotel;
import com.yash.project.HotelManagementApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByPaymentSessionId(String sessionId);

    List<Booking> findByHotel(Hotel hotel);

    List<Booking> findByHotelAndCreatedAtBetween(Hotel hotel, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<BookingDto> findByUser(User user);
}
