package com.yash.project.HotelManagementApp.repository;

import com.yash.project.HotelManagementApp.entity.Hotel;
import com.yash.project.HotelManagementApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findByOwner(User user);
}
