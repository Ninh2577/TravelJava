package com.example.DTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangDTO {
    private Integer idChiTietGioHang;
    private String tenTour;
    private Integer idBienThe;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Float giaNguoiLon;
    private Float giaTreEm;
    private Integer soNguoi;
    private Float tongTien;
    private String soNgay;
}
