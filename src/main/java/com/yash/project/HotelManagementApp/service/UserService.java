package com.yash.project.HotelManagementApp.service;

import com.yash.project.HotelManagementApp.dto.UserDto;
import com.yash.project.HotelManagementApp.dto.UserProfileUpdateRequestDto;
import com.yash.project.HotelManagementApp.entity.User;
import org.jspecify.annotations.Nullable;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(UserProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
