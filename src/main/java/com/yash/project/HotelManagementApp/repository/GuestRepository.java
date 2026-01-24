package com.yash.project.HotelManagementApp.repository;

import com.yash.project.HotelManagementApp.entity.Guest;
import com.yash.project.HotelManagementApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUser(User user);
}
