package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
            // Gọi service để lưu yêu thích
            YeuThich yeuThich = yeuThichService.saveYeuThich(yeuThichDTO.getUserId(), yeuThichDTO.getTourId());

            // Tạo đối tượng DTO từ Entity để trả về
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
            // Gọi repository để lấy danh sách tour yêu thích của người dùng
            List<Tour> likedTours = yeuThichRepository.findLikedToursByUserId(userId);
            
            // Nếu không có tour yêu thích nào, trả về phản hồi thích hợp
            if (likedTours.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tour yêu thích nào.");
            }

            return ResponseEntity.ok(likedTours);
        } catch (Exception e) {
            // Trường hợp có lỗi hệ thống
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống.");
        }
    }
    
//  @PostMapping("/add")
//  public ResponseEntity<?> addYeuThich(@RequestBody YeuThichDTO dto) {
//      try {
//          // Gửi cả ngày bắt đầu và ngày kết thúc
//          yeuThichService.saveYeuThich(dto.getUserId(), dto.getTourId(), dto.getStartDate(), dto.getEndDate());
//          return ResponseEntity.ok(Collections.singletonMap("message", "Yêu thích đã được lưu thành công"));
//      } catch (Exception e) {
//          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
//      }
//  }
}
