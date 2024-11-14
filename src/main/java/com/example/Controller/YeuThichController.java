package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Entity.YeuThich;
import com.example.service.YeuThichService;

@RestController
@RequestMapping("/api/yeuthich")
@CrossOrigin(origins = "http://localhost:3000")
public class YeuThichController {

    @Autowired
    private YeuThichService yeuThichService;

    // GET Phương thức Yêu Thích
    @GetMapping
    public ResponseEntity<List<YeuThich>> getAllYeuThich() {
        List<YeuThich> yeuThichs = yeuThichService.getAllYeuThich();
        return ResponseEntity.ok(yeuThichs);
    }

    // POST Phương thức để lưu trạng thái yêu thích của người dùng
    @PostMapping("/save")
    public ResponseEntity<YeuThich> saveOrUpdateYeuThich(@RequestParam Integer userId, @RequestParam Integer tourId, @RequestParam boolean thich) {
        YeuThich yeuThich = yeuThichService.saveOrUpdateYeuThich(userId, tourId, thich);
        return ResponseEntity.ok(yeuThich);
    }
}
