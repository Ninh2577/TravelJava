package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ChiTietHoaDonDTO;
import com.example.Repository.HoaDonRepository;
import com.example.service.HoaDonService;

@RestController
@RequestMapping("/api")
public class LichSuDatTour {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/lich-su-dat-tour/{userId}") // Thay đổi để nhận ID người dùng từ URL
    public ResponseEntity<List<ChiTietHoaDonDTO>> getAllLichSuDatTour(@PathVariable Integer userId) {
        try {
            System.out.println("userId: " + userId); // Log userId nhận được

            List<ChiTietHoaDonDTO> lichSuDatTour = hoaDonRepository.findHoaDonByUserId(userId);
            return ResponseEntity.ok(lichSuDatTour);
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi nếu có
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/huy")
    public ResponseEntity<?> huyHoaDon(@RequestBody Map<String, Object> payload) {
        Integer chiTietHoaDonId = (Integer) payload.get("chiTietHoaDonId");
        String cancelReason = (String) payload.get("cancelReason"); // Lý do hủy được lấy từ payload
        System.out.println("Ngày bắt đầu: " + chiTietHoaDonId);
        System.out.println("form người dùng: " + cancelReason);

        try {
            hoaDonService.huyHoaDon(chiTietHoaDonId, cancelReason); // Truyền lý do hủy vào service
            return ResponseEntity.ok("Hủy hóa đơn thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
