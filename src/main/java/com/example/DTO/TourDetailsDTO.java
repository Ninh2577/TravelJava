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
    private Date ngayBatDau;
    private float giaNguoiLon;
    private String tenPhuongTien;
    private Integer danhGiaKhachSan;
}
