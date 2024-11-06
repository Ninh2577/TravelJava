package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatTourDTO2 {
    private Integer id;
    private Date ngayDat;
    private String tenTour;
    private int soLuongNguoi;
    private boolean trangThai;
    private boolean phuongThucThanhToan;
}
