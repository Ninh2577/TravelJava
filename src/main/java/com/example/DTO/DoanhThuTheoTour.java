package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoanhThuTheoTour {
    private Date nam;
    private String tenTour;
    private double tongTien;
}
