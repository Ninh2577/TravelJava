package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoanhThuDTO {
    private int nam;
    private String loaiTour;
    private double tongDoanhThu;
    private long tongHoaDon;
}
