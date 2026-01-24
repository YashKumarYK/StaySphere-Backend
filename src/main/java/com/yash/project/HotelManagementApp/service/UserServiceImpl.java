package com.yash.project.HotelManagementApp.service;

import com.yash.project.HotelManagementApp.Exception.ResourceNotFoundException;
import com.yash.project.HotelManagementApp.dto.UserDto;
import com.yash.project.HotelManagementApp.dto.UserProfileUpdateRequestDto;
import com.yash.project.HotelManagementApp.entity.Hotel;
import com.yash.project.HotelManagementApp.entity.User;
import com.yash.project.HotelManagementApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.yash.project.HotelManagementApp.util.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long id) {
        log.info("Getting the user with id:{}",id);
        return userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+id));
    }

    @Override
    public void updateProfile(UserProfileUpdateRequestDto profileUpdateRequestDto) {
        User user = getCurrentUser();
        if(profileUpdateRequestDto.getDateOfBirth() !=null) user.setDateOfBirth(profileUpdateRequestDto.getDateOfBirth());
        if (profileUpdateRequestDto.getName() != null) user.setName(profileUpdateRequestDto.getName());
        if (profileUpdateRequestDto.getGender() != null) user.setGender(profileUpdateRequestDto.getGender());

        userRepository.save(user);
    }

    @Override
    public UserDto getMyProfile() {
        User user = getCurrentUser();
        log.info("Getting the profile for the user with the id: {}",user.getId());
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails)userRepository.findByEmail(username).orElse(null);
    }
}
