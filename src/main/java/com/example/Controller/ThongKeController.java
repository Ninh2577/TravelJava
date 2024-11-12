package com.example.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.DoanhThuDTO;
import com.example.Repository.HoaDonRepository;

@RestController
@RequestMapping("/api/thongke")
@CrossOrigin(origins = "http://localhost:3000") // Cấu hình cho frontend

public class ThongKeController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @GetMapping("/doanh-thu")
    public List<Object[]> getRevenueByDateRange(@RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) throws Exception {
        // Chuyển đổi chuỗi ngày tháng sang đối tượng Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);
        System.out.println("Doanh thu trong khoảng thời gian từ " + startDateStr + " đến " + endDateStr + ":");

        // Truy vấn doanh thu
        return hoaDonRepository.findRevenueByDateRange(startDate, endDate);
    }

    @GetMapping("/tongSoTourDuocDat")
    public List<DoanhThuDTO> getTourPeopleCount(@RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) throws Exception {
        // Chuyển đổi chuỗi ngày tháng sang đối tượng Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);
        System.out.println("Doanh thu trong khoảng thời gian từ " + startDateStr + " đến " + endDateStr + ":");

        // Truy vấn doanh thu
        return hoaDonRepository.findTourStatisticsByDateRange(startDate, endDate);
    }

}
