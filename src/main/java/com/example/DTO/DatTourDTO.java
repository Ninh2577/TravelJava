package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
public class DatTourDTO {
	    private Integer ids; // ID của Đặt Tour
	    private String tenKhachHangs; // Họ tên khách hàng
	    private String soDienThoais; // Số điện thoại khách hàng
	    private String emails; // Email khách hàng
	    private boolean trangThais; // Trạng thái Đặt Tour
	    private Date ngayDats; // Ngày đặt tour
	    private String tenTours; // Tên Tour
	    private Integer idTours; // ID của Tour
	    private int soLuongNguois; // Số lượng người tối đa trong Tour
	    private int soNguois; // Số người đã đặt
	    private Double tiens; // Tiền đã đặt
	    private Double giamGias; // Giảm giá
	    private Double tongTiens; // Tổng tiền
	    private String qrCodes; // Mã QR
	}

