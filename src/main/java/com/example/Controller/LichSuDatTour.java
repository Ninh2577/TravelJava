package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ChiTietHoaDonDTO;
import com.example.Repository.HoaDonRepository;

@RestController
@RequestMapping("/api")
public class LichSuDatTour {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @GetMapping("/lich-su-dat-tour/{userId}") // Thay đổi để nhận ID người dùng từ URL
    public ResponseEntity<List<ChiTietHoaDonDTO>> getAllLichSuDatTour(@PathVariable Integer userId) {
        try {
            List<ChiTietHoaDonDTO> lichSuDatTour = hoaDonRepository.findHoaDonByUserId(userId);
            return ResponseEntity.ok(lichSuDatTour);
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi nếu có
            return ResponseEntity.badRequest().body(null);
        }
    }

}
