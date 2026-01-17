package com.yash.project.HotelManagementApp.service;


import com.yash.project.HotelManagementApp.entity.Room;

public interface InventoryService{

    void initializeRoomForYear(Room room);

    void deleteFutureInventory(Room room);
}
