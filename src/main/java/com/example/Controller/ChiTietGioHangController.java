package com.example.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.example.DTO.ChiTietGioHangRequestDTO;
import com.example.DTO.GioHangDTO;
import com.example.Entity.ChiTietGioHang;
import com.example.Entity.MediaTour;
import com.example.service.ChiTietGioHangService;

@RestController
@RequestMapping("/api/chitietgiohang")
@CrossOrigin(origins = "http://localhost:3000")
public class ChiTietGioHangController {

	ChiTietGioHang chiTietGioHang = new ChiTietGioHang();

	@Autowired
	private ChiTietGioHangService chiTietGioHangService;

	@GetMapping
	public ResponseEntity<List<ChiTietGioHang>> getAllChiTietGioHang() {
		List<ChiTietGioHang> chiTietGioHangs = chiTietGioHangService.getAllChiTietGioHang();
		return ResponseEntity.ok(chiTietGioHangs);
	}

	@PostMapping("/add")
	public ResponseEntity<ChiTietGioHang> addChiTietGioHang(@RequestBody ChiTietGioHangRequestDTO dto) {
		chiTietGioHang = chiTietGioHangService.saveChiTietGioHang(dto);
		return ResponseEntity.ok(chiTietGioHang);
	}

	// @GetMapping("/bychitietgiohang/{id}")
	// public List<ChiTietGioHang> getChiTietGiohangByMediaTourId(@PathVariable
	// Integer id) {
	// return chiTietGioHangService.getChiTietGiohangByBienTheTourId(id);
	// }

	@GetMapping("/bychitietgiohang/{idBienTheTour}/{idNguoiDung}")
	public List<ChiTietGioHang> getChiTietGiohangByMediaTourIdAndNguoiDungId(
			@PathVariable Integer idBienTheTour,
			@PathVariable Integer idNguoiDung) {
		return chiTietGioHangService.getChiTietGiohangByBienTheTourIdAndNguoiDungId(idBienTheTour, idNguoiDung);
	}

	@GetMapping("/check")
	public ResponseEntity<Map<String, Boolean>> checkChiTietGioHang(@RequestParam int idNguoiDung,
			@RequestParam int idBienTheTour) {

		boolean exists = chiTietGioHangService.checkIfExists(idNguoiDung, idBienTheTour);

		Map<String, Boolean> response = new HashMap<>();
		response.put("exists", exists);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/another-endpoint/{idDanhSachGioHang}")
	public ResponseEntity<?> getChiTietGioHangById(@PathVariable Integer idDanhSachGioHang) {
		// Gọi đến service để lấy dữ liệu từ database
		Optional<ChiTietGioHang> chiTietGioHang = chiTietGioHangService.findById(idDanhSachGioHang);

		if (chiTietGioHang.isPresent()) {
			return ResponseEntity.ok(chiTietGioHang.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Không tìm thấy ChiTietGioHang với id: " + idDanhSachGioHang);
		}
	}

	// cập nhật số lượng người trong chi tiết giỏ hàng
	@PutMapping("/updateSoNguoi/{id}")
	public ResponseEntity<Void> updateSoNguoi(@PathVariable Integer id) {
		chiTietGioHangService.updateSoNguoi(id);
		return ResponseEntity.ok().build();
	}

	// -----------------------------------------------
	@GetMapping("/user/{idNguoiDung}")
	public ResponseEntity<List<GioHangDTO>> getCartDetailsByUserId(@PathVariable Integer idNguoiDung) {
		List<GioHangDTO> cartDetails = chiTietGioHangService.getCartDetailsByUserId(idNguoiDung);
		return ResponseEntity.ok(cartDetails);
	}

	// API để xóa giỏ hàng theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteChiTietGioHang(@PathVariable Integer id) {
		try {
			// Gọi service để xóa giỏ hàng
			chiTietGioHangService.deleteChiTietGioHang(id);
			// Trả về thông báo xóa thành công
			return ResponseEntity.ok("Xóa giỏ hàng thành công!");
		} catch (IllegalArgumentException e) {
			// Trả về thông báo lỗi nếu không tìm thấy giỏ hàng
			return ResponseEntity.status(404).body("Giỏ hàng không tồn tại!");
		}
	}
}
