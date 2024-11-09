package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.*;

import com.example.DTO.DoanhThuDTO;
import com.example.service.DoanhThuService;

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:3000")

public class DoanhThuController {

    @Autowired
    DoanhThuService doanhThuService;

    // API để lấy doanh thu theo năm
    @GetMapping("/doanh-thu")
    public List<DoanhThuDTO> getDoanhThuByYear(@RequestParam("year") int year) {
        // Gọi service để lấy doanh thu từ repository
        return doanhThuService.getDoanhThuByYear(year);
    }
}
