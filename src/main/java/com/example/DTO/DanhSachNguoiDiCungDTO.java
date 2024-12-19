package com.example.DTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanhSachNguoiDiCungDTO {
    private Integer id;
    private String hoTen;
    private String email;
    private String soDienThoai;
    private Date namSinh;
    private String tenTour; // Tên tour
    private Date ngayBatDau; 
    private boolean trangThai; // Trạng thái
    private boolean phuongThucThanhToan; // Phương thức thanh toán
    private Date ngayDat; // Ngày đặt tour
    private long soNguoi; // Số lượng người đi cùng
    private double tongTien; // Tổng tiền
}
