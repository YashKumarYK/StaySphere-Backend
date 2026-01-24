package com.yash.project.HotelManagementApp.dto;

import com.yash.project.HotelManagementApp.entity.Hotel;
import com.yash.project.HotelManagementApp.entity.Room;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InventoryDto {
    private Long id;
    private LocalDate date;
    private Integer bookedCount;
    private Integer reservedCount;
    private Integer totalCount;
    private BigDecimal surgeFactor;
    private BigDecimal price; // basePrice * surgeFactor
    private Boolean closed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
