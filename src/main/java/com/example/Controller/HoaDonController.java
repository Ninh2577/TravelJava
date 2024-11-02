package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.HoaDon;
import com.example.service.HoaDonService;
import com.example.service.HotelsService;

@RestController
@RequestMapping("/api/hoadon")
@CrossOrigin(origins = "http://localhost/3000")
public class HoaDonController {

	@Autowired
	private HoaDonService hoaDonService;
	
	@GetMapping
	public ResponseEntity<List<HoaDon>> getAllHoaDon(){
		List<HoaDon> hoaDons = hoaDonService.getAllHoaDon();
		return ResponseEntity.ok(hoaDons);
	}
}
