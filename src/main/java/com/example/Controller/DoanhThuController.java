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
    public List<DoanhThuDTO> getDoanhThuByYearRange(
            @RequestParam("startYear") int startYear,
            @RequestParam("endYear") int endYear) {

        System.out.println("Start Year: " + startYear + ", End Year: " + endYear);
        return doanhThuService.getDoanhThuByYear(startYear, endYear);
    }

    // Endpoint lấy danh sách các năm
    @GetMapping("/years")
    public List<Integer> getAllDistinctYears() {
        return doanhThuService.getAllDistinctYears();
    }
}
