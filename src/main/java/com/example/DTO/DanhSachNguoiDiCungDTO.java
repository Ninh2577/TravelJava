package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanhSachNguoiDiCungDTO {
    private String hoTen;
    private String email;
    private String soDienThoai;
    private Date namSinh;
}
