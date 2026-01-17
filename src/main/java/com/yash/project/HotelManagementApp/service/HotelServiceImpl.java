package com.yash.project.HotelManagementApp.service;

import com.yash.project.HotelManagementApp.Exception.ResourceNotFoundException;
import com.yash.project.HotelManagementApp.dto.HotelDto;
import com.yash.project.HotelManagementApp.entity.Hotel;
import com.yash.project.HotelManagementApp.entity.Room;
import com.yash.project.HotelManagementApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new Hotel with name:{}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
        hotel=hotelRepository.save(hotel);
        log.info("Created a hotel with ID:{}",hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {

        log.info("Getting the hotel with id:{}",id);
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id:"+id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating the hotel with id:{}",id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id:"+id));

        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Transactional
    @Override
    public void deleteHotelById(Long id) {
        log.info("Deleting the hotel with id:{}", id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id:" + id));

        hotelRepository.deleteById(id);

        for(Room room: hotel.getRooms()){
            inventoryService.deleteFutureInventory(room);
        }

    }

    @Transactional
    @Override
    public void activateHotel(Long HotelId) {
        log.info("Activating the hotel with id:{}", HotelId);

        Hotel hotel = hotelRepository.findById(HotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id:" + HotelId));
        hotel.setActive(true);

        //assuming only do it once
        for(Room room : hotel.getRooms()){
            inventoryService.initializeRoomForYear(room);
        }

    }
}
