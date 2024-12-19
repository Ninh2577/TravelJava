package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDetailsDTO {
    private Integer id;
    private String tenTour;
    private String hinhAnh;
    private String soNgay;
    private boolean trangThai;
    private Date ngayBatDau;
    private float giaNguoiLon;
    private String tenPhuongTien;
    private Integer danhGiaKhachSan;

    public TourDetailsDTO(Integer id, String tenTour, String soNgay, Date ngayBatDau, float giaNguoiLon,
            String hinhAnh) {
        this.id = id;
        this.tenTour = tenTour;
        this.soNgay = soNgay;
        this.ngayBatDau = ngayBatDau;
        this.giaNguoiLon = giaNguoiLon;
        this.hinhAnh = hinhAnh;
    }
}