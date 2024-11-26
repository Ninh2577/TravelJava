package com.example.Controller;

import com.example.Entity.PhanQuyen;
import com.example.Entity.ChucNang;
import com.example.Entity.NguoiDung;
import com.example.DTO.PhanQuyenRequest;
import com.example.service.PhanQuyenService;
import com.example.service.ChucNangService;
import com.example.service.NguoiDungService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/phan-quyen")
@CrossOrigin(origins = "http://localhost:3000")
public class PhanQuyenController {

    @Autowired
    private PhanQuyenService phanQuyenService;

    @Autowired
    private ChucNangService chucNangService;

    @Autowired
    private NguoiDungService nguoiDungService;

    // Lấy tất cả quyền
    @GetMapping
    public List<PhanQuyen> getAllPhanQuyen() {
        return phanQuyenService.getAllPhanQuyen();
    }

    // Lấy tất cả người dùng (email)
    @GetMapping("/nguoi-dung")
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungService.getAllNguoiDungs();
    }

    // Lấy tất cả chức năng
    @GetMapping("/chucnang")
    public List<ChucNang> getAllChucNang() {
        return chucNangService.getAllChucNang();
    }

    // cấp quyền cho người dùng
    @PostMapping
    public ResponseEntity<String> capQuyen(@RequestBody PhanQuyenRequest request) {
        if (request.getIdChucNang() == null || request.getIdChucNang().isEmpty()) {
            return ResponseEntity.badRequest().body("Danh sách chức năng không thể rỗng");
        }

        System.out.println("Request nhận được: " + request);
        
        String message = phanQuyenService.capQuyen(request.getEmailNhanVien(), request.getIdChucNang());
        return ResponseEntity.ok(message);
    }


    // Xóa quyền
    @DeleteMapping("/nguoi-dung/{idNguoiDung}")
    public ResponseEntity<Void> deleteAllPhanQuyenByUserId(@PathVariable Integer idNguoiDung) {
        try {     
            phanQuyenService.deleteAllPhanQuyenByUserId(idNguoiDung);
            return ResponseEntity.noContent().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); 
        }
    }
    
    @GetMapping("/nguoi-dung-voi-chuc-nang")
    public ResponseEntity<List<Map<String, Object>>> getNguoiDungWithChucNang() {
        List<Map<String, Object>> data = phanQuyenService.getNguoiDungWithChucNang();
        return ResponseEntity.ok(data);
    }

}
