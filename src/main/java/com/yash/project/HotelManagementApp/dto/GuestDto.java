package com.yash.project.HotelManagementApp.dto;

import com.yash.project.HotelManagementApp.entity.User;
import com.yash.project.HotelManagementApp.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
