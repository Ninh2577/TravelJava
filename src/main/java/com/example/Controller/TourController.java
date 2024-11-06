package com.example.Controller;

import java.util.List;
import java.util.Optional;

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
import com.example.Entity.Tour;
import com.example.projection.TourDetailsProjection;
import com.example.service.TourService;

@RestController
@RequestMapping("/api/tours")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {

	@Autowired
	private TourService tourService;

	@GetMapping
	public ResponseEntity<List<Tour>> getAllTour() {
		List<Tour> tours = tourService.getAllTours();
		return ResponseEntity.ok(tours);
	}

	// API POST: Thêm tour mới
	@PostMapping("/them")
	public ResponseEntity<Tour> addTour(@RequestBody Tour tour) {
		Tour savedTour = tourService.addTour(tour);
		return ResponseEntity.ok(savedTour);
	}

	// API GET: Lấy thông tin chi tiết tour theo ID
	@GetMapping("/{id}")
	public ResponseEntity<Tour> getTourById(@PathVariable Integer id) {
		Optional<Tour> tour = tourService.getTourById(id);
		if (tour.isPresent()) {
			return ResponseEntity.ok(tour.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// API PUT: Cập nhật tour theo ID
	@PutMapping("/update/{id}")
	public ResponseEntity<Tour> updateTour(@PathVariable("id") Integer id, @RequestBody Tour updatedTour) {
		Tour tour = tourService.updateTour(id, updatedTour);
		if (tour != null) {
			return ResponseEntity.ok(tour);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// API DELETE: xóa tour theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTour(@PathVariable Integer id) {
		try {
			tourService.deleteTour(id);
			return new ResponseEntity<>("Người dùng đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
