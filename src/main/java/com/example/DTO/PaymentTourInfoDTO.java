package com.example.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTourInfoDTO {
    private Integer paymentId; // ID của thanh toán
    private String customerName; // Tên khách hàng
    private String phoneNumber; // Số điện thoại
    private String email; // Địa chỉ email
    private String tourName; // Tên tour
    private Integer numberOfPeople; // Số người
    private Double totalAmount; // Tổng số tiền
    private Date paymentDate; // Ngày thanh toán
    private boolean paymentStatus; // Trạng thái thanh toán
    private Integer soLuongNguoi; // Số lượng người trong tour
}
