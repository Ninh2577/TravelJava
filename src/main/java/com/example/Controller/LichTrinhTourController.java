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

@RestController
@RequestMapping("/api/lichtrinhtour")
@CrossOrigin(origins = "http://localhost:3000")
public class LichTrinhTourController {

	@Autowired
	private LichTrinhTourService lichTrinhTourService;
	
	@GetMapping
	public ResponseEntity<List<LichTrinhTour>> getAllLichTrinhTour(){
		List<LichTrinhTour> lichTrinhTours = lichTrinhTourService.getAllLichTrinhTours();
		return ResponseEntity.ok(lichTrinhTours);
	}
	
	// API POST :Thêm lịch trình tour
	@PostMapping("/them")
	public ResponseEntity<LichTrinhTour> addLichTrinhTour(@RequestBody LichTrinhTour lichTrinhTour){
		LichTrinhTour savelichTrinhTour = lichTrinhTourService.addLichTrinhTour(lichTrinhTour);
		return ResponseEntity.ok(savelichTrinhTour);
	}
	
	//API PUT : Cập nhật lịch trình tour theo ID
	@PutMapping("/update/{id}")
	public ResponseEntity<LichTrinhTour> updateLichTrinhTour(@PathVariable("id") Integer id, @RequestBody LichTrinhTour updateLichTrinhTour){
		LichTrinhTour lichTrinhTour = lichTrinhTourService.updateLichTrinhTour(id, updateLichTrinhTour);
		if(lichTrinhTour != null) {
			return ResponseEntity.ok(lichTrinhTour);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//API DELETE : Xóa lịch trình tour theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLichTringTour(@PathVariable Integer id){
		try {
			lichTrinhTourService.deleteLichTrinhTour(id);
			return new ResponseEntity<> ("Lịch trình tour đã được xóa thành công: " , HttpStatus.OK); 
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
