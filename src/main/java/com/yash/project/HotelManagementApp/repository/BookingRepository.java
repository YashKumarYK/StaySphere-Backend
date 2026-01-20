package com.yash.project.HotelManagementApp.repository;

import com.yash.project.HotelManagementApp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
