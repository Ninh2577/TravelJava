package com.example.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.DatTourDTO;
import com.example.Entity.DatTour;
import com.example.service.DatTourService;

@RestController
@RequestMapping("/api/dat-tour")
@CrossOrigin(origins = "http://localhost:3000")
public class DatTourController {

	@Autowired
	private DatTourService datTourService;

	// API lấy hết thông tin Đặt Tour
	@GetMapping
	public List<DatTour> getAllDatTours() {
		return datTourService.findAll();
	}

	// API lấy thông tin chi tiết Đặt Tour theo ID
	@GetMapping("/{id}")
	public ResponseEntity<DatTour> getDatTourById(@PathVariable Integer id) {
		Optional<DatTour> datTour = datTourService.findById(id);
		if (datTour.isPresent()) {
			return ResponseEntity.ok(datTour.get());
		} else {
			return ResponseEntity.notFound().build(); // Return 404 if not found
		}
	}
	@GetMapping("/info/{id}")
	public ResponseEntity<DatTourDTO> getDatTourInfo(@PathVariable Integer id) {
	    Optional<DatTour> datTourOptional = datTourService.findById(id);

	    if (datTourOptional.isPresent()) {
	        DatTour datTour = datTourOptional.get();

	        // Tạo DTO từ Đặt Tour với các trường cần thiết
	        DatTourDTO paymentInfo = new DatTourDTO(
	                datTour.getId(),
	                datTour.getNguoiDung().getHoTen(),
	                datTour.getNguoiDung().getSoDienThoai(), 
	                datTour.getNguoiDung().getEmail(),
	                datTour.isTrangThai(),
	                datTour.getNgayDat(),
	                datTour.getTour().getTenTour(),
	                datTour.getTour().getId(), // ID của Tour
	                datTour.getTour().getSoLuongNguoi(), 
	                datTour.getSoNguoi(),
	                datTour.getTien(), 
	                datTour.getTienGiam(),
	                datTour.getTongTien(),
	                datTour.getQR() // Mã QR
	        );

	        return ResponseEntity.ok(paymentInfo);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
