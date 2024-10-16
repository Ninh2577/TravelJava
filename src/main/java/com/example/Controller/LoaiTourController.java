package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.LoaiTour;
import com.example.service.LoaiTourService;

@RestController
@RequestMapping("/api/loaitours")
@CrossOrigin(origins = "http://localhost:3000")
public class LoaiTourController {
	@Autowired
	private LoaiTourService loaiTourService;

	@GetMapping
	public ResponseEntity<List<LoaiTour>> getAllLoaiTour() {
		List<LoaiTour> loaiTours = loaiTourService.getAllLoaiTours();
		return ResponseEntity.ok(loaiTours);
	}

	// API POST: Thêm loại tour mới
	@PostMapping("/them")
	public ResponseEntity<LoaiTour> addLoaiTour(@RequestBody LoaiTour loaiTour) {
		LoaiTour savedLoaiTour = loaiTourService.addLoaiTour(loaiTour);
		return ResponseEntity.ok(savedLoaiTour);
	}

	// API PUT: Cập nhật Loại tour theo ID
	@PutMapping("/update/{id}")
	public ResponseEntity<LoaiTour> updateLoaiTour(@PathVariable("id") Integer id,
			@RequestBody LoaiTour updatedLoaiTour) {
		LoaiTour loaiTour = loaiTourService.updateLoaiTour(id, updatedLoaiTour);
		if (loaiTour != null) {
			return ResponseEntity.ok(loaiTour);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	// API xóa loại tour theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLoaiTour(@PathVariable Integer id) {
		try {
			loaiTourService.deleteLoaiTour(id);
			return new ResponseEntity<>("Người dùng đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
