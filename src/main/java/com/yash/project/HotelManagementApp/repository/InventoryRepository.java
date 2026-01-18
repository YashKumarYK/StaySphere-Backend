package com.yash.project.HotelManagementApp.repository;

import com.yash.project.HotelManagementApp.entity.Inventory;
import com.yash.project.HotelManagementApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByRoom(Room room);
}
