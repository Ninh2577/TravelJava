package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.ChucNang;
import com.example.service.ChucNangService;
import com.example.service.NguoiDungService;


@RestController
@RequestMapping("/api/chuc-nang")
@CrossOrigin(origins = "http://localhost:3000")
public class ChucNangController {
	@Autowired
    private ChucNangService chucNangService;

    // Lấy danh sách tất cả chức năng
    @GetMapping
    public ResponseEntity<List<ChucNang>> getAllChucNang() {
        List<ChucNang> chucNangs = chucNangService.getAllChucNang();
        return ResponseEntity.ok(chucNangs);
    }
}

