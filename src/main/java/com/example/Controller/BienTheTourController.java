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
import com.example.service.BienTheTourService;

@RestController
@RequestMapping("/api/bienthetour")
@CrossOrigin(origins ="http://localhost:3000")
public class BienTheTourController {
	
	@Autowired 
	private BienTheTourService bienTheTourService;
	
	@GetMapping
	public ResponseEntity<List<BienTheTour>> getAllBienTheTour(){
		List<BienTheTour> bienTheTour = bienTheTourService.getAllBienTheTour();
		return ResponseEntity.ok(bienTheTour);
	}
	//API POST : thêm biến thể tour theo ID
	@PostMapping("/them")
	public ResponseEntity<BienTheTour> addBienTheTour(@RequestBody BienTheTour bienTheTour){
		BienTheTour savedBienTheTour = bienTheTourService.addBienTheTour(bienTheTour);
		return ResponseEntity.ok(savedBienTheTour);
	}
	//API PUT : Cập nhật biến thể tour theo ID
	@PutMapping("/update/{id}")
	public ResponseEntity<BienTheTour> updateBienTheTour(@PathVariable ("id") Integer id ,@RequestBody BienTheTour updateBienTheTour){
		BienTheTour bienTheTour = bienTheTourService.updateBienTheTour(id, updateBienTheTour);
		if(bienTheTour != null) {
			return ResponseEntity.ok(bienTheTour);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	//API DELETE : Xóa biến thể tour theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBienTheTour(@PathVariable Integer id){
		try {
			bienTheTourService.deleteBienTheTour(id);
			return new ResponseEntity<>("Người dùng đã được xóa thành công", HttpStatus.OK);
			
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
