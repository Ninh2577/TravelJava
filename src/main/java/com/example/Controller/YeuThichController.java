package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.YeuThichDTO;
import com.example.Entity.Tour;
import com.example.Entity.YeuThich;
import com.example.Repository.YeuThichRepository;
import com.example.service.YeuThichService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/yeuthich")
@CrossOrigin(origins = "http://localhost:3000")
public class YeuThichController {

    @Autowired
    private YeuThichService yeuThichService;
    
    @Autowired
    private YeuThichRepository yeuThichRepository;
    
    @PostMapping("/add")
    public ResponseEntity<?> addYeuThich(@RequestBody YeuThichDTO yeuThichDTO) {
        try {
            YeuThich yeuThich = yeuThichService.saveYeuThich(yeuThichDTO.getUserId(), yeuThichDTO.getTourId());
            YeuThichDTO savedYeuThichDTO = new YeuThichDTO(
                    yeuThich.getNguoiDung().getId(), 
                    yeuThich.getTour().getId()
            ); 
            return ResponseEntity.ok(savedYeuThichDTO);
        } catch (RuntimeException e) {
            Logger logger = LoggerFactory.getLogger(YeuThichController.class);
            logger.error("Lỗi khi thêm YeuThich", e);
            return ResponseEntity.status(404).body("Không tìm thấy người dùng hoặc tour.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống.");
        }
    }
    
    @GetMapping("/likedTours/{userId}")
    public ResponseEntity<?> getLikedToursByUserId(@PathVariable Integer userId) {
        try {
             List<Tour> likedTours = yeuThichRepository.findLikedToursByUserId(userId);
           if (likedTours.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tour yêu thích nào.");
            }
            return ResponseEntity.ok(likedTours);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống.");
        }
    }
    
    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<?> deleteAllLikedTours(@PathVariable Integer userId) {
        try {
            yeuThichService.deleteAllLikedToursByUserId(userId);
            return ResponseEntity.ok("Đã xóa tất cả các tour yêu thích của người dùng ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi xóa dữ liệu.");
        }
    }
}
//package com.example.Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.Entity.YeuThich;
//import com.example.service.YeuThichService;
//
//@RestController
//@RequestMapping("/api/yeuthich")
//@CrossOrigin(origins = "http://localhost:3000")
//public class YeuThichController {
//
//    @Autowired
//    private YeuThichService yeuThichService;
//
//    // GET Phương thức lấy danh sách yêu thích
//    @GetMapping
//    public ResponseEntity<List<YeuThich>> getAllYeuThich() {
//        List<YeuThich> yeuThichs = yeuThichService.getAllYeuThich();
//        return ResponseEntity.ok(yeuThichs);
//    }
//
//    // POST Phương thức lưu yêu thích
//    @PostMapping("/add")
//    public ResponseEntity<YeuThich> addYeuThich(
//            @RequestParam Integer userId,
//            @RequestParam Integer tourId) {
//        YeuThich yeuThich = yeuThichService.saveYeuThich(userId, tourId);
//        return ResponseEntity.ok(yeuThich);
//    }
//}
