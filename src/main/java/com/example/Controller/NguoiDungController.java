package com.example.Controller;

import com.example.Entity.NguoiDung;
import com.example.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nguoidung")
@CrossOrigin(origins = "http://localhost:3000")
public class NguoiDungController {

	@Autowired
	private NguoiDungService nguoiDungService;

	// API lấy danh sách tất cả người dùng
	@GetMapping
	public ResponseEntity<List<NguoiDung>> getNguoiDungByRole(@RequestParam("vaiTroId") Integer vaiTroId) {
		List<NguoiDung> nguoiDungs = nguoiDungService.getNguoiDungByVaiTroId(vaiTroId);
		return ResponseEntity.ok(nguoiDungs);
	}

	// API lấy thông tin chi tiết người dùng theo ID
	@GetMapping("/{id}")
	public Optional<NguoiDung> getNguoiDungById(@PathVariable Integer id) {
		return nguoiDungService.getNguoiDungById(id);
	}

	// API thêm người dùng
	@PostMapping("/add")
	public ResponseEntity<NguoiDung> addNguoiDung(@RequestBody NguoiDung nguoiDung) {
		NguoiDung savedNguoiDung = nguoiDungService.addNguoiDung(nguoiDung);
		return new ResponseEntity<>(savedNguoiDung, HttpStatus.CREATED);
	}

	// API cập nhật người dùng theo ID
	@PutMapping("/update/{id}")
	public ResponseEntity<NguoiDung> updateNguoiDung(@PathVariable Integer id,
			@RequestBody NguoiDung updatedNguoiDung) {
		try {
			NguoiDung nguoiDung = nguoiDungService.updateNguoiDung(id, updatedNguoiDung);
			return new ResponseEntity<>(nguoiDung, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// API xóa người dùng theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteNguoiDung(@PathVariable Integer id) {
		try {
			nguoiDungService.deleteNguoiDung(id);
			return new ResponseEntity<>("Người dùng đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// API tìm kiếm người dùng
	@GetMapping("/timkiem")
	public ResponseEntity<List<NguoiDung>> searchNguoiDung(@RequestParam(required = false) String hoTen,
			@RequestParam(required = false) String soDienThoai, @RequestParam(required = false) String email,
			@RequestParam(required = false) String diaChi, @RequestParam(required = false) Integer tuoi) {

		List<NguoiDung> result = nguoiDungService.searchNguoiDung(hoTen, soDienThoai, email, diaChi, tuoi);
		return ResponseEntity.ok(result);
	}

	// API sắp xếp người dùng theo ID (tăng dần hoặc giảm dần)
	@GetMapping("/sapxep")
	public ResponseEntity<List<NguoiDung>> sortNguoiDungById(@RequestParam("order") String order) {
	    List<NguoiDung> nguoiDungs;
	    
	    if (order.equalsIgnoreCase("asc")) {
	        nguoiDungs = nguoiDungService.sortNguoiDungByIdAsc();
	    } else if (order.equalsIgnoreCase("desc")) {
	        nguoiDungs = nguoiDungService.sortNguoiDungByIdDesc();
	    } else {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Invalid order parameter
	    }

	    return ResponseEntity.ok(nguoiDungs);
	}

}
