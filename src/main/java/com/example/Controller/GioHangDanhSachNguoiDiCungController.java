package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.GioHangNguoiDiCungDTO;
import com.example.Entity.ChiTietGioHang;
import com.example.Entity.GioHangDanhSachNguoiDiCung;
import com.example.service.GioHangDanhSachNguoiDiCungService;

@RestController
@RequestMapping("/api/giohangdanhsachnguoidicung")
@CrossOrigin(origins = "http://localhost:3000")
public class GioHangDanhSachNguoiDiCungController {

	@Autowired
	private GioHangDanhSachNguoiDiCungService gioHangDanhSachNguoiDiCungService;

	@GetMapping
	public ResponseEntity<List<GioHangDanhSachNguoiDiCung>> getAllGioHangDanhSachNguoiDiCung() {
		List<GioHangDanhSachNguoiDiCung> gioHangDanhSachNguoiDiCung = gioHangDanhSachNguoiDiCungService
				.getAllGioHangDanhSachNguoiDiCung();
		return ResponseEntity.ok(gioHangDanhSachNguoiDiCung);
	}

	// API POST : Thêm mới Giỏ hàng danh sách người đi cùng
	@PostMapping("/them")
	public ResponseEntity<GioHangDanhSachNguoiDiCung> addGioHangDanhSachNguoiDiCung(
			@RequestBody GioHangDanhSachNguoiDiCung gioHangDanhSachNguoiDiCung) {
		GioHangDanhSachNguoiDiCung savedGioHangDanhSachNguoiDiCung = gioHangDanhSachNguoiDiCungService
				.addGioHangDanhSachNguoiDiCung(gioHangDanhSachNguoiDiCung);
		return ResponseEntity.ok(savedGioHangDanhSachNguoiDiCung);
	}

	@GetMapping("/bygiohangdanhsachnguoidicung/{id}")
	public List<GioHangDanhSachNguoiDiCung> getGioHangDanhSachNguoiDiCungByChiTietGioHangId(@PathVariable Integer id) {
		return gioHangDanhSachNguoiDiCungService.getGioHangDanhSachNguoiDiCungByChiTietGioHangId(id);
	}

	@GetMapping("/danhSachNguoiDiCung")
	public List<GioHangNguoiDiCungDTO> getDanhSachNguoiDiCung(@RequestParam Integer idChiTietGioHang) {
		return gioHangDanhSachNguoiDiCungService.getDanhSachNguoiDiCung(idChiTietGioHang);
	}
}
