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

import com.example.Entity.GiaTour;
import com.example.service.GiaTourService;

@RestController
@RequestMapping("/api/giatour")
@CrossOrigin(origins = "http://localhost/3000")
public class GiaTourController {

	@Autowired
	private GiaTourService giaTourService;
	// API GET : Lấy hết thông tin Loại tour
		@GetMapping
		public ResponseEntity<List<GiaTour>> getAllGiaTour(){
			List<GiaTour> giaTours = giaTourService.getAllGiaTour();
			return ResponseEntity.ok(giaTours);
		}
		//API POST : Thêm mới Loại Tour
		@PostMapping("/them")
		public ResponseEntity<GiaTour> addGiaTour(@RequestBody GiaTour giaTour){
			GiaTour savedGiaTour = giaTourService.addGiaTour(giaTour);
			return ResponseEntity.ok(savedGiaTour);
		}
		//API PUT : Cập nhật Loại Tour 
			@PutMapping("/update/{id}")
			public ResponseEntity<GiaTour> updateGiaTour(@PathVariable("id") Integer id, @RequestBody GiaTour updateGiaTour){
				GiaTour giaTour = giaTourService.updatedGiaTour(id, updateGiaTour);
				if(giaTour != null) {
					return ResponseEntity.ok(giaTour);		
				}else {
					return ResponseEntity.notFound().build();
				}
			}
			// API DELETE: Xóa Loại tour theo ID
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<String> deleteGiaTour(@PathVariable Integer id){
				try {
					giaTourService.deleteGiaTour(id);
					return new ResponseEntity<> ("Giá Tour đã được xóa thành công", HttpStatus.OK);
					
				}catch(RuntimeException e){
					return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
				}
			}
}
