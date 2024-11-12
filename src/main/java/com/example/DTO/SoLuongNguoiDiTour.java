package com.example.DTO;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SoLuongNguoiDiTour {
    private Date nam;
    private String tenTour;
    private Long soLuongNguoi;
}
