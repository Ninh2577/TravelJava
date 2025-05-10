package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.ChiTietHoaDon;
import com.example.service.ChiTietHoaDonService;

@RestController
@RequestMapping("/api/dattour")
@CrossOrigin(origins = "http://localhost/3000")
public class ChiTietHoaDonController {
	@Autowired 
	private ChiTietHoaDonService datTourService;
	
	@GetMapping
	public ResponseEntity<List<ChiTietHoaDon>> getAllDatTour(){
		List<ChiTietHoaDon> chiTietHoaDons = datTourService.getAllChiTietHoaDons();
		return ResponseEntity.ok(chiTietHoaDons);
	} 
}	