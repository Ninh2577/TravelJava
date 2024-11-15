package com.example.DTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {
	 private Integer idChiTietGioHang; 
    private Integer idNguoiDung; // User ID
    private float tongTien; // Total amount
    private boolean phuongThucThanhToan; // Payment method (true for cash, false for online)
    private boolean trangThai;
    private Date ngayThanhToan; // Payment date
    private Integer idBienTheTour;  // ID for BienTheTour
   // ID for ChiTietGioHang
}
