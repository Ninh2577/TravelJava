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

import com.example.Entity.PhuongTien;
import com.example.service.PhuongTienService;

@RestController
@RequestMapping("/api/phuongtien")
@CrossOrigin(origins = "http://localhost/3000")
public class PhuongTienController {

	@Autowired
	private PhuongTienService phuongTienService;

	// API GET : Lấy hết thông tin Phương tiện
	@GetMapping
	public ResponseEntity<List<PhuongTien>> getAllPhuongTien() {
		List<PhuongTien> phuongTiens = phuongTienService.getAllPhuongTiens();
		return ResponseEntity.ok(phuongTiens);
	}

	// API POST : Thêm mới Phương tiện
	@PostMapping("/them")
	public ResponseEntity<PhuongTien> addPhuongTien(@RequestBody PhuongTien phuongTien) {
		PhuongTien savedPhuongTien = phuongTienService.addPhuongTien(phuongTien);
		return ResponseEntity.ok(savedPhuongTien);
	}

	// API PUT : Cập nhật Phương tiện
	@PutMapping("/update/{id}")
	public ResponseEntity<PhuongTien> updatePhuongTien(@PathVariable("id") Integer id,
			@RequestBody PhuongTien updatePhuongTien) {
		PhuongTien phuongTien = phuongTienService.updatePhuongTien(id, updatePhuongTien);
		if (phuongTien != null) {
			return ResponseEntity.ok(phuongTien);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// API DELETE : Xóa Phương tiện
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePhuongTien(@PathVariable Integer id) {
		try {
			phuongTienService.deletePhuongTien(id);
			return new ResponseEntity<>("Phương tiện đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
