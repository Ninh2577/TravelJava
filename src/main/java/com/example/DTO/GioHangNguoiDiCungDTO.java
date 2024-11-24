package com.example.DTO;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangNguoiDiCungDTO implements Serializable {
    private String hoTen;
    private Date namSinh;
}
