package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDonDTO {
    private Integer id;
    private Date ngayDat;
    private String tenTour;
    private int soLuongNguoi;
    private double thanhTien; // Added this field based on the query
    private boolean trangThai;
    private boolean phuongThucThanhToan;
}
