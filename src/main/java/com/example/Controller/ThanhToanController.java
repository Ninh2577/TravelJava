package com.example.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.PaymentTourInfoDTO;
import com.example.Entity.ThanhToan;
import com.example.service.ThanhToanService;

@RestController
@RequestMapping("/api/thanh-toan")
@CrossOrigin(origins = "http://localhost:3000")
public class ThanhToanController {
	@Autowired
	private ThanhToanService thanhToanService;

	// API to get all ThanhToan records
	@GetMapping
	public List<ThanhToan> getAllThanhToan() {
		return thanhToanService.getAllThanhToan();
	}
	// API để lấy chi tiết ThanhToan theo ID
    @GetMapping("/{id}")
    public Optional<ThanhToan> getThanhToanById(@PathVariable Integer id) {
        return thanhToanService.getThanhToanById(id);
    }
    @GetMapping("/info/{paymentId}")
    public ResponseEntity<PaymentTourInfoDTO> getPaymentInfo(@PathVariable Integer paymentId) {
        Optional<ThanhToan> thanhToanOptional = thanhToanService.getThanhToanById(paymentId);

        if (thanhToanOptional.isPresent()) {
            ThanhToan thanhToan = thanhToanOptional.get();

            // Tạo PaymentTourInfoDTO từ ThanhToan
            PaymentTourInfoDTO paymentInfo = new PaymentTourInfoDTO(
                thanhToan.getId(), // ID của thanh toán
                thanhToan.getNguoiDung().getHoTen(), // Tên khách hàng
                thanhToan.getNguoiDung().getSoDienThoai(), // Số điện thoại
                thanhToan.getNguoiDung().getEmail(), // Địa chỉ email
                thanhToan.getTenTour(), // Tên tour
                thanhToan.getDatTour().getTour().getSoLuongNguoi(),// Số người
                thanhToan.getTongTien(), // Tổng số tiền
                thanhToan.getNgayThanhToan(), // Ngày thanh toán
                thanhToan.isTrangThai(), // Phương thức thanh toán
                thanhToan.getDatTour().getSoNguoi() // Số lượng người trong tour
            );

            return ResponseEntity.ok(paymentInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
