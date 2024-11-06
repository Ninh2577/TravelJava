package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.DanhSachNguoiDiCung;
import com.example.Entity.NguoiDung;
import com.example.service.DanhSachNguoiDiCungService;

@RestController
@RequestMapping("/api/danhsachnguoidicung")
@CrossOrigin(origins = "http://localhost/3000")
public class DanhSachNguoiDiCungController {

	@Autowired
	private DanhSachNguoiDiCungService danhSachNguoiDiCungService;

	// GET Phương thức Danh sách người đi cùng
	@GetMapping
	public ResponseEntity<List<DanhSachNguoiDiCung>> getAllDanhSachNguoiDiCung() {
		List<DanhSachNguoiDiCung> danhSachNguoiDiCungs = danhSachNguoiDiCungService.getAllDanhSachNguoiDiCung();
		return ResponseEntity.ok(danhSachNguoiDiCungs);
	}

}
