package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Entity.BaiViet;
import com.example.Entity.NguoiDung;
import com.example.Repository.BaiVietRepository;
import com.example.Repository.NguoiDungRepository;
import com.example.service.BaiVietService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bai-viet")
@CrossOrigin(origins = "http://localhost:3000")
public class BaiVietController {
	@Autowired
	private BaiVietService baiVietService;
	
	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@GetMapping
	public List<BaiViet> getAllBaiViet() {
		return baiVietService.getBaiViet();
	}

	// API lấy bài viết theo ID
	@GetMapping("/{id}")
	public ResponseEntity<BaiViet> getBaiVietById(@PathVariable Integer id) {
		Optional<BaiViet> baiViet = baiVietService.getBaiVietById(id);
		return baiViet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// API thêm bài viết
//	@PostMapping("/add")
//	public ResponseEntity<BaiViet> addNguoiDung(@RequestBody BaiViet baiViet) {
//		BaiViet save = baiVietService.addBaiViet(baiViet);
//		return new ResponseEntity<>(save, HttpStatus.CREATED);
//	}
		
	@PostMapping("/add")
	public ResponseEntity<?> addBaiViet(@RequestBody BaiViet baiViet) {
	    // Lấy ID của người dùng từ bài viết
	    Integer idNguoiDung = baiViet.getNguoiDung().getId();
	    
	    // Tìm người dùng theo ID từ cơ sở dữ liệu
	    NguoiDung nguoiDung = nguoiDungRepository.findById(idNguoiDung)
	            .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + idNguoiDung));
	    
	    // Gán đối tượng NguoiDung vào BaiViet
	    baiViet.setNguoiDung(nguoiDung);
	    
	    // Lưu bài viết
	    BaiViet savedBaiViet = baiVietService.addBaiViet(baiViet);
	    
	    return new ResponseEntity<>(savedBaiViet, HttpStatus.CREATED);
	}


    // API cập nhật bài viết theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBaiViet(@PathVariable Integer id, @RequestBody BaiViet baiViet) {
        try {
            // Lấy ID của người dùng từ bài viết
            Integer idNguoiDung = baiViet.getNguoiDung().getId();
            
            // Tìm người dùng theo ID từ cơ sở dữ liệu
            NguoiDung nguoiDung = nguoiDungRepository.findById(idNguoiDung)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + idNguoiDung));
            
            // Gán đối tượng NguoiDung vào BaiViet
            baiViet.setNguoiDung(nguoiDung);
            
            // Cập nhật bài viết
            BaiViet updatedBaiViet = baiVietService.updateBaiViet(id, baiViet);
            
            return new ResponseEntity<>(updatedBaiViet, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
	
	// API xóa bài viết theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBaiViet(@PathVariable Integer id) {
		try {
			baiVietService.deleteBaiViet(id);
			return new ResponseEntity<>("Bài viết đã xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
