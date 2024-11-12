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

import com.example.Entity.BienTheTour;
import com.example.Repository.BienTheTourRepository;
import com.example.service.BienTheTourService;

@RestController
@RequestMapping("/api/bienthetour")
@CrossOrigin(origins = "http://localhost/3000")
public class BienTheTourController {

	@Autowired
	private BienTheTourService bienTheTourService;
	@Autowired
	private BienTheTourRepository bienTheTourRepository;

	@GetMapping
	public ResponseEntity<List<BienTheTour>> getAllBienTheTour() {
		List<BienTheTour> bienTheTours = bienTheTourService.getAllBienTheTour();
		return ResponseEntity.ok(bienTheTours);
	}

	// API POST : Thêm mới Loại Tour
	@PostMapping("/them")
	public ResponseEntity<BienTheTour> addBienTheTour(@RequestBody BienTheTour bientheTour) {
		BienTheTour savedBienTheTour = bienTheTourService.addBienTheTour(bientheTour);
		return ResponseEntity.ok(savedBienTheTour);
	}

	// API PUT : Cập nhật Loại Tour
	@PutMapping("/update/{id}")
	public ResponseEntity<BienTheTour> updateBienTheTour(@PathVariable("id") Integer id,
			@RequestBody BienTheTour updateLoaiTour) {
		BienTheTour bienTheTour = bienTheTourService.updatedBienTheTour(id, updateLoaiTour);
		if (bienTheTour != null) {
			return ResponseEntity.ok(bienTheTour);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// API DELETE: Xóa Loại tour theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBienTheTour(@PathVariable Integer id) {
		try {
			bienTheTourService.deleteBienTheTour(id);
			return new ResponseEntity<>("Biến Thể Tour đã được xóa thành công", HttpStatus.OK);

		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/tour/{tourId}")
	public List<BienTheTour> getBienTheToursByTourId(@PathVariable Integer tourId) {
		return bienTheTourService.getBienTheTourByTourId(tourId);
	}


    @GetMapping("/tours")
    public List<BienTheTour> getAllBienTheTours() {
        return bienTheTourRepository.findAll();
    }
}
