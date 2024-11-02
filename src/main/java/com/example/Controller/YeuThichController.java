package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.YeuThich;
import com.example.service.YeuThichService;

@RestController
@RequestMapping("/api/yeuthich")
@CrossOrigin(origins = "http://localhost:3000")
public class YeuThichController {

	@Autowired
	private YeuThichService yeuThichService;

	// GET Phương thức Yêu Thích
	@GetMapping
	public ResponseEntity<List<YeuThich>> getAllYeuThich() {
		List<YeuThich> yeuThichs = yeuThichService.getAllYeuThich();
		return ResponseEntity.ok(yeuThichs);
	}
}
