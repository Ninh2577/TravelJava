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

import com.example.Entity.DanhMucTour;
import com.example.service.DanhMucTourService;

@RestController
@RequestMapping("/api/danhmuctour")
@CrossOrigin(origins = "http://localhost:3000")
public class DanhMucTourController {

	@Autowired 
	private DanhMucTourService danhMucTourService;
	
	@GetMapping
	public ResponseEntity<List<DanhMucTour>> getAllDanhMucTour(){
		List<DanhMucTour> danhMucTours = danhMucTourService.getAllDanhMucTour();
		return ResponseEntity.ok(danhMucTours);
	}
	@PostMapping("/them")
	public ResponseEntity<DanhMucTour> addTour(@RequestBody DanhMucTour danhMucTour) {
		DanhMucTour savedDanhMucTour = danhMucTourService.addDanhMucTour(danhMucTour);
		return ResponseEntity.ok(savedDanhMucTour);
	}
	
	// API PUT: Cập nhật tour theo ID
	@PutMapping("/update/{id}")
	public ResponseEntity<DanhMucTour> updateDanhMucTour(@PathVariable("id") Integer id, @RequestBody DanhMucTour updatedDanhMucTour) {
		DanhMucTour danhMucTour = danhMucTourService.updateDanhMucTour(id, updatedDanhMucTour);
		if (danhMucTour != null) {
			return ResponseEntity.ok(danhMucTour);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	// API DELETE: xóa tour theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDanhMucTour(@PathVariable Integer id) {
		try {
			danhMucTourService.deleteDanhMucTour(id);
			return new ResponseEntity<>("Người dùng đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
