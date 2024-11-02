package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.ChiTietGioHang;
import com.example.service.ChiTietGioHangService;

@RestController
@RequestMapping("/api/chitietgiohang")
@CrossOrigin(origins = "http://localhost:3000")
public class ChiTietGioHangController {

	@Autowired
	private ChiTietGioHangService chiTietGioHangService;
	
	@GetMapping
	public ResponseEntity<List<ChiTietGioHang>> getAllChiTietGioHang(){
		List<ChiTietGioHang> chiTietGioHangs = chiTietGioHangService.getAllChiTietGioHang();
		return ResponseEntity.ok(chiTietGioHangs);
	}
}
