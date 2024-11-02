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

import com.example.Entity.Hotels;
import com.example.service.HotelsService;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin (origins = "http://localhost//3000")
public class HotelsController {

	@Autowired
	private HotelsService hotelsService;
	//GET hết thông tin Hotels
	@GetMapping
	public ResponseEntity<List<Hotels>> getAllHotels(){
		List<Hotels> hotels = hotelsService.getAllHotels();
		return ResponseEntity.ok(hotels);
	}
	//API POST : Thêm mới Hotels
	@PostMapping("/them")
	public ResponseEntity<Hotels> addHotels(@RequestBody Hotels hotels){
		Hotels savedHotels = hotelsService.addHotels(hotels);
		return ResponseEntity.ok(savedHotels);
		}
	//API PUT : Cập nhật Hotels
	@PutMapping("/update/{id}")
	public ResponseEntity<Hotels> updateHotels(@PathVariable("id") Integer id, @RequestBody Hotels updateHotels){
		Hotels hotels = hotelsService.updateHotels(id, updateHotels);
		if(hotels != null) {
			return ResponseEntity.ok(hotels);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	//API DELETE : Xóa Hotels
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteHotels(@PathVariable Integer id){
		try {
			hotelsService.deleteHotels(id);
			return new ResponseEntity<>("Hotels đã được xóa thành công" , HttpStatus.OK);
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new ResponseEntity<> (e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
