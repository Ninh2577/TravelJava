package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.DatTourDTO2;
import com.example.Repository.DatTourRepository;

@RestController
@RequestMapping("/api")
public class LichSuDatTour {
    @Autowired
    private DatTourRepository datTourRepository;

    @GetMapping("/lich-su-dat-tour/{userId}") // Thay đổi để nhận ID người dùng từ URL
    public ResponseEntity<List<DatTourDTO2>> getAllLichSuDatTour(@PathVariable Integer userId) {
        try {
            List<DatTourDTO2> lichSuDatTour = datTourRepository.findDatToursByUserId(userId);
            return ResponseEntity.ok(lichSuDatTour);
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi nếu có
            return ResponseEntity.badRequest().body(null);
        }
    }

}
