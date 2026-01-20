package com.yash.project.HotelManagementApp.repository;

import com.yash.project.HotelManagementApp.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
