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

import com.example.Entity.LichTrinhTour;
import com.example.service.LichTrinhTourService;
import com.example.service.TourService;

@RestController
@RequestMapping("/api/lichtrinhtour")
@CrossOrigin(origins = "http://localhost:3000")
public class LichTrinhTourController {

	@Autowired
	private LichTrinhTourService lichTrinhTourService;
	@Autowired
	private TourService tourService;

	// GET Phương thức Lịch trình tour
	@GetMapping
	public ResponseEntity<List<LichTrinhTour>> getAllLichTrinhTour() {
		List<LichTrinhTour> lichTrinhTours = lichTrinhTourService.getAllLichTrinhTour();
		return ResponseEntity.ok(lichTrinhTours);
	}

// API POST : Thêm Lịch trình tour 
	@PostMapping("/them")
	public ResponseEntity<LichTrinhTour> addLichTrinhTour(@RequestBody LichTrinhTour lichTrinhTour) {
		LichTrinhTour savedlichTrinhTour = lichTrinhTourService.addLichTrinhTour(lichTrinhTour);
		return ResponseEntity.ok(savedlichTrinhTour);
	}

	// API PUT: Cập nhật tour theo ID
	@PutMapping("/update/{id}")
	public ResponseEntity<LichTrinhTour> updateTour(@PathVariable("id") Integer id,
			@RequestBody LichTrinhTour updatedLichTrinhTour) {
		LichTrinhTour lichTrinhTour = lichTrinhTourService.updatedLichTrinhTour(id, updatedLichTrinhTour);
		if (lichTrinhTour != null) {
			return ResponseEntity.ok(lichTrinhTour);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// API DELETE: xóa tour theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLichTrinhTour(@PathVariable Integer id) {
		try {
			lichTrinhTourService.deleteLichTrinhTour(id);
			return new ResponseEntity<>("Lịch Trình Tour đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
//GET thông tin lịch trình tour theo ID
	@GetMapping("/tour/{tourId}")
	public ResponseEntity<List<LichTrinhTour>> getLichTrinhByTourId(@PathVariable Integer tourId) {
		List<LichTrinhTour> lichTrinhTours = lichTrinhTourService.getLichTrinhTourByTourId(tourId);
		return ResponseEntity.ok(lichTrinhTours);
	}
}
