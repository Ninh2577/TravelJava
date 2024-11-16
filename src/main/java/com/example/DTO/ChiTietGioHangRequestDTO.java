package com.example.DTO;

import lombok.Data;

@Data
public class ChiTietGioHangRequestDTO {
    private Integer idNguoiDung;
    private Integer idBienTheTour;
    private float tongTien;
    private String moTa;
    private Integer soNguoi;
}