package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDonsDTO {

    private Integer hoaDonId;
    private Integer nguoiDungId;
    private float tongTien;
    private Date ngayThanhToan;
    private boolean phuongThucThanhToan;
    private boolean hoaDonTrangThai;
    private String ghiChu;
    
    private Integer chiTietHoaDonId;
    private Date ngayDat;
    private boolean chiTietHoaDonTrangThai;
    private float thanhTien;
    private float giaNguoiLon;
    private float giaTreEm;
    private String moTa;
    private Integer bienTheTourId;
    
    
    private Integer danhSachNguoiDiCungId;
    private String hoTen;
    private String email;
    private String soDienThoai;
    private Date namSinh;
    
    private String nguoiDungHoTen;
    private String nguoiDungSoDienThoai;
    private String nguoiDungEmail;
    private String nguoiDungDiaChi;
    private String tenTour;	
}