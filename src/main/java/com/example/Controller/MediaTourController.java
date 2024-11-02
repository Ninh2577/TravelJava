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

import com.example.Entity.MediaTour;
import com.example.Entity.Tour;
import com.example.service.MediaTourService;

@RestController
@RequestMapping("/api/mediatour")
@CrossOrigin(origins = "http://localhost:3000")
public class MediaTourController {

	@Autowired
	private MediaTourService mediaTourService;
	
	//GET Phương thức MediaTour
	@GetMapping
	public ResponseEntity<List<MediaTour>> getAllMediaTour(){
		List<MediaTour> mediaTours = mediaTourService.getAllMediaTour();
		return ResponseEntity.ok(mediaTours);
	}
	// API POST : Phương thức thêm MediaTour
	@PostMapping("/them")
	public ResponseEntity<MediaTour> addMediaTour (@RequestBody MediaTour mediaTour){
		MediaTour savedMediaTour = mediaTourService.addMediaTour(mediaTour);
		return ResponseEntity.ok(savedMediaTour);
	}
	// API PUT: Cập nhật MediaTour theo ID
		@PutMapping("/update/{id}")
		public ResponseEntity<MediaTour> updateMediaTour(@PathVariable("id") Integer id, @RequestBody MediaTour updatedMediaTour) {
			MediaTour mediaTour = mediaTourService.updatedMediaTour(id, updatedMediaTour);
			if (mediaTour!= null) {
				return ResponseEntity.ok(mediaTour);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		// API DELETE: xóa tour theo ID
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteMediaTour(@PathVariable Integer id) {
			try {
				mediaTourService.deleteMediaTour(id);
				return new ResponseEntity<>("MediaTour đã được xóa thành công", HttpStatus.OK);
			} catch (RuntimeException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}
}
