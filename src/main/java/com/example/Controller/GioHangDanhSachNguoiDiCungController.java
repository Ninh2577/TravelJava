package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.GioHangDanhSachNguoiDiCung;
import com.example.service.GioHangDanhSachNguoiDiCungService;
@RestController
@RequestMapping("/api/giohangdanhsachnguoidicung")
@CrossOrigin(origins = "http://localhost:3000")
public class GioHangDanhSachNguoiDiCungController {

	@Autowired
	private GioHangDanhSachNguoiDiCungService gioHangDanhSachNguoiDiCungService;
	
	@GetMapping
	public ResponseEntity<List<GioHangDanhSachNguoiDiCung>> getAllGioHangDanhSachNguoiDiCung(){
		List<GioHangDanhSachNguoiDiCung> gioHangDanhSachNguoiDiCung = gioHangDanhSachNguoiDiCungService.getAllGioHangDanhSachNguoiDiCung();
		return ResponseEntity.ok(gioHangDanhSachNguoiDiCung);
}
}
