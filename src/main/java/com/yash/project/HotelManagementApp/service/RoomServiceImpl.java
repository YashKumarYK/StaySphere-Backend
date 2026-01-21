package com.yash.project.HotelManagementApp.service;

import com.yash.project.HotelManagementApp.Exception.ResourceNotFoundException;
import com.yash.project.HotelManagementApp.Exception.UnAuthorisedException;
import com.yash.project.HotelManagementApp.dto.RoomDto;
import com.yash.project.HotelManagementApp.entity.Hotel;
import com.yash.project.HotelManagementApp.entity.Room;
import com.yash.project.HotelManagementApp.entity.User;
import com.yash.project.HotelManagementApp.repository.HotelRepository;
import com.yash.project.HotelManagementApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    public final ModelMapper modelMapper;
    public final RoomRepository roomRepository;
    public final HotelRepository hotelRepository;
    public final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Getting the hotel with id:{}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).
                orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id:"+hotelId));

        //checking authorization
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.equals(hotel.getOwner())){
            throw new UnAuthorisedException("This user does not own this hotel with id: "+hotelId);
        }

        log.info("Creating a new room");
        Room room = modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room=roomRepository.save(room);
        log.info("Created a room with ID:{}",roomDto.getId());

        if(hotel.getActive()){
            inventoryService.initializeRoomForYear(room);
        }
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {

        log.info("Getting the room in Hotel with id:{}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).
                orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id:"+hotelId));

        return hotel.getRooms().stream().map((element)-> modelMapper.map(element, RoomDto.class)).collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the room with ID: {}", roomId);


        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    @Transactional
    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room with ID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));

        //checking authorization
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.equals(room.getHotel().getOwner())){
            throw new UnAuthorisedException("This user does not own this room with id: "+roomId);
        }

        inventoryService.deleteAllInventories(room);
        roomRepository.deleteById(roomId);

    }
}
