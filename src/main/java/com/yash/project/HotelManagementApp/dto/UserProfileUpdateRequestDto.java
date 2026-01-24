package com.yash.project.HotelManagementApp.dto;

import com.yash.project.HotelManagementApp.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileUpdateRequestDto {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
