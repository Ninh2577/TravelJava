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

	// -------------------------------------------------------------------
	@DeleteMapping("/delete-giohangnguoidicung/{chiTietGioHangId}")
	public ResponseEntity<String> deleteGiohangNguoiDiCung(@PathVariable Long chiTietGioHangId) {
		System.out.println("id: " + chiTietGioHangId);
		try {
			gioHangDanhSachNguoiDiCungService.deleteByChiTietGioHangId(chiTietGioHangId);
			return ResponseEntity.ok("Xóa thông tin người đi cùng thành công!");
		} catch (Exception e) {
			e.printStackTrace(); // In chi tiết exception ra console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Lỗi khi xóa thông tin người đi cùng: " + e.getMessage());
		}
	}

	// -------------------------------------
	@PutMapping("/cap-nhat")
	public ResponseEntity<List<GioHangDanhSachNguoiDiCung>> updateGioHangDanhSachNguoiDiCung(
			@RequestBody List<GioHangDanhSachNguoiDiCung> danhSachNguoiDiCung) {
		try {
			List<GioHangDanhSachNguoiDiCung> updatedList = gioHangDanhSachNguoiDiCungService
					.updateGioHangDanhSachNguoiDiCung(danhSachNguoiDiCung);
			return ResponseEntity.ok(updatedList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}


	// @DeleteMapping("/delete2-giohangnguoidicung/{id}")
	// public ResponseEntity<String> deleteGiohangNguoiDiCungs(@PathVariable Integer id) {
	// 	System.out.println("id: " + id);
	// 	try {
	// 		// Gọi service để xóa theo ID giỏ hàng người đi cùng
	// 		gioHangDanhSachNguoiDiCungService.delete(id);
	// 		return ResponseEntity.ok("Xóa thông tin người đi cùng thành công!");
	// 	} catch (Exception e) {
	// 		e.printStackTrace(); // In chi tiết exception ra console
	// 		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	// 				.body("Lỗi khi xóa thông tin người đi cùng: " + e.getMessage());
	// 	}
	// }

	@DeleteMapping("/delete2-giohangnguoidicung")
	public ResponseEntity<String> deleteGiohangNguoiDiCungs(@RequestBody List<Integer> ids) {
		try {
			// Gọi service để xóa các giỏ hàng người đi cùng theo danh sách ID
			gioHangDanhSachNguoiDiCungService.deleteMultiple(ids);
			return ResponseEntity.ok("Xóa thông tin người đi cùng thành công!");
		} catch (Exception e) {
			e.printStackTrace(); // In chi tiết exception ra console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Lỗi khi xóa thông tin người đi cùng: " + e.getMessage());
		}
	}



}
