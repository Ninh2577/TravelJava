package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoanhThuDTO {
    private Date nam;
    // private String tenTour;
    private Long soLuongTour;
}
