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

import com.example.Entity.GiamGia;
import com.example.service.GiamGiaService;

@RestController
@RequestMapping("/api/giamgia")
@CrossOrigin(origins = "http://localhost:3000")
public class GiamGiaController {
	
	@Autowired
	private GiamGiaService giamGiaService;
	
	@GetMapping
	public ResponseEntity<List<GiamGia>> getAllGiamGia(){
		List<GiamGia> giamGias = giamGiaService.getAllGiamGias();
		return ResponseEntity.ok(giamGias);
	}
	//API POST : Thêm mới Giảm giá
	@PostMapping("/them")
	public ResponseEntity<GiamGia> addGiamGia(@RequestBody GiamGia giamGia){
		GiamGia savedGiamGia = giamGiaService.addGiamGia(giamGia);
		return ResponseEntity.ok(savedGiamGia);
	}
	//API PUT : Cập nhật giảm giá mới
	@PutMapping("/update/{id}")
	public ResponseEntity<GiamGia> updateGiamGia(@PathVariable("id") Integer id, @RequestBody GiamGia updatedGiamGia){
		GiamGia giamGia = giamGiaService.updatedGiamGia(id, updatedGiamGia);
		if(giamGia != null) {
			return ResponseEntity.ok(giamGia);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	//API DELETE : Xóa Giảm giá mới
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteGiamGia(@PathVariable Integer id){
		try {
			giamGiaService.deleteGiamGia(id);
			return new ResponseEntity<> ("Giảm giá đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
