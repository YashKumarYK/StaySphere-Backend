package com.yash.project.HotelManagementApp.repository;

import com.yash.project.HotelManagementApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
