package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.DanhGia;
import com.example.service.DanhGiaService;

@RestController
@RequestMapping("/api/danhgia")
@CrossOrigin(origins = "http://localhost/3000")
public class DanhGiaController {

	@Autowired
	private DanhGiaService danhGiaService;
	// GET Phương thức Đánh giá
	@GetMapping
	public ResponseEntity<List<DanhGia>> getAllDanhGia(){
		List<DanhGia> danhGias = danhGiaService.getAllDanhGia();
		return ResponseEntity.ok(danhGias);
	}
}
