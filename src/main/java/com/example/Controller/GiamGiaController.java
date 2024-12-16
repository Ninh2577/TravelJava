package com.example.Controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.BienTheTour;
import com.example.Entity.GiamGia;
import com.example.service.BienTheTourService;
import com.example.service.GiamGiaService;

@RestController
@RequestMapping("/api/giamgia")
@CrossOrigin(origins = "http://localhost:3000")
public class GiamGiaController {

	@Autowired
	private BienTheTourService bienTheTourService;

	@Autowired
	private GiamGiaService giamGiaService;

	@GetMapping
	public ResponseEntity<List<GiamGia>> getAllGiamGia() {
		List<GiamGia> giamGias = giamGiaService.getAllGiamGias();
		return ResponseEntity.ok(giamGias);
	}

	// API POST : Thêm mới Giảm giá
	@PostMapping("/them")
	public ResponseEntity<GiamGia> addGiamGia(@RequestBody GiamGia giamGia) {
		GiamGia savedGiamGia = giamGiaService.addGiamGia(giamGia);
		return ResponseEntity.ok(savedGiamGia);
	}

	// API PUT : Cập nhật giảm giá mới
	@PutMapping("/update/{id}")
	public ResponseEntity<GiamGia> updateGiamGia(@PathVariable("id") Integer id, @RequestBody GiamGia updatedGiamGia) {
		GiamGia giamGia = giamGiaService.updatedGiamGia(id, updatedGiamGia);
		if (giamGia != null) {
			return ResponseEntity.ok(giamGia);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// API DELETE : Xóa Giảm giá mới
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteGiamGia(@PathVariable Integer id) {
		try {
			giamGiaService.deleteGiamGia(id);
			return new ResponseEntity<>("Giảm giá đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/kiemtra/{maGiamGia}/{bienTheTourId}")
	public ResponseEntity<String> applyDiscountCode(
			@PathVariable String maGiamGia,
			@PathVariable Integer bienTheTourId) {

		// System.out.println("Received request with maGiamGia: " + maGiamGia + " and bienTheTourId: " + bienTheTourId);

		// Lấy thông tin biến thể tour (BienTheTour)
		BienTheTour bienTheTour = bienTheTourService.findById(bienTheTourId);
		// System.out.println("BienTheTour found: " + bienTheTour);

		if (bienTheTour == null) {
			// System.out.println("BienTheTour not found for id: " + bienTheTourId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Biến thể tour không tồn tại");
		}

		// Tìm mã giảm giá từ cơ sở dữ liệu
		GiamGia giamGia = giamGiaService.findByMaGiamGia(maGiamGia);
		// System.out.println("GiamGia found: " + giamGia);

		if (giamGia == null) {
			// System.out.println("GiamGia not found for code: " + maGiamGia);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mã giảm giá không tồn tại");
		}

		// Kiểm tra xem mã giảm giá có áp dụng cho biến thể tour này không
		// System.out.println("Checking if GiamGia applies to BienTheTour...");
		if (!giamGia.getBienTheTours().contains(bienTheTour)) {
			// System.out.println("GiamGia does not apply to the given BienTheTour");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Mã giảm giá không áp dụng cho biến thể tour này");
		}

		// Kiểm tra ngày bắt đầu và kết thúc của mã giảm giá
		Date today = new Date();
		System.out.println("Today's date: " + today);
		System.out.println("GiamGia start date: " + giamGia.getNgayBatDau());
		System.out.println("GiamGia end date: " + giamGia.getNgayKetThuc());

		if (today.before(giamGia.getNgayBatDau()) || today.after(giamGia.getNgayKetThuc())) {
			// System.out.println("GiamGia is expired or not yet started");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã giảm giá hết hạn hoặc chưa bắt đầu");
		}

		// Tính toán giảm giá dựa trên tỷ lệ phần trăm
		float tongTien = bienTheTour.getGiaNguoiLon() * 1; // Ví dụ tính cho 1 người lớn
		// System.out.println("Original price (tongTien): " + tongTien);

		float discountAmount = tongTien * giamGia.getPhanTram() / 100; // Áp dụng phần trăm giảm giá
		// System.out.println("Discount percentage: " + giamGia.getPhanTram() + "%");
		// System.out.println("Discount amount: " + discountAmount);

		// Cập nhật giá trị giảm giá trong giỏ hàng (ChiTietGioHang)
		float finalPrice = tongTien - discountAmount;
		// System.out.println("Final price after discount: " + finalPrice);

		// Trả về kết quả
		return ResponseEntity
				.ok("Mã giảm giá hợp lệ. Giá trị giảm: " + discountAmount + ". Tổng sau giảm: " + finalPrice);
	}

}
