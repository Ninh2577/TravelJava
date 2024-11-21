package com.example.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.DoanhThuTheoTour;
import com.example.DTO.SoLuongNguoiDiTour;
import com.example.DTO.SoLuongTourDaDatDTO;
import com.example.Repository.HoaDonRepository;
import com.example.service.HoaDonService;
import com.example.service.ThongKeService;

@RestController
@RequestMapping("/api/thongke")
@CrossOrigin(origins = "http://localhost:3000") // Cấu hình cho frontend

public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/bieu-do-doanh-thu")
    public List<Object[]> thongKeTongDoanhThuTheoTG(
            @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
            @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
        return thongKeService.thongKeTongDoanhThuTheoTG(ngayBatDau, ngayKetThuc);
    }

    // API thống kê tổng số lượng tour được đặt
    // @GetMapping("/bieu-do-so-luong-tour")
    // public List<SoLuongTourDaDatDTO> thongKeTourTheoKhoangThoiGian(
    //         @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
    //         @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
    //     return thongKeService.thongKeTourTheoKhoangThoiGian(ngayBatDau, ngayKetThuc);
    // }
    @GetMapping("/bieu-do-so-luong-tour")
    public List<Object[]> thongKeTourTheoKhoangThoiGian(
            @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
            @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
        return thongKeService.countBookedToursByDate(ngayBatDau, ngayKetThuc);
    }

    // API thống kê số lượng người tham gia mỗi tour
    // @GetMapping("/table-so-luong-nguoi-tham-gia")
    // public List<SoLuongNguoiDiTour> thongKeSoLuongNguoiDiTourTheoKhoangThoiGian(
    //         @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
    //         @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
    //     return thongKeService.thongKeSoLuongNguoiDiTourTheoKhoangThoiGian(ngayBatDau, ngayKetThuc);
    // }
    @GetMapping("/table-so-luong-nguoi-tham-gia")
    public List<Object[]> thongKeSoLuongNguoiDiTourTheoKhoangThoiGian(
            @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
            @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {

        // Gọi phương thức trong service để lấy dữ liệu
        return thongKeService.thongKeSoLuongNguoiDiTourTheoKhoangThoiGian(ngayBatDau, ngayKetThuc);
    }



    // API thống kê doanh thu theo tour
    @GetMapping("/table-doanh-thu-theo-tour")
    public List<DoanhThuTheoTour> thongKeDoanhThuTheoTourTrongKhoangThoiGian(
            @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
            @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
        return thongKeService.thongKeDoanhThuTheoTourTrongKhoangThoiGian(ngayBatDau, ngayKetThuc);
    }
    
    @GetMapping("/tong-doanh-thu")
    public ResponseEntity<Double> getTotalAmountInRange(
        @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
        @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {

        Double totalAmount = thongKeService.calculateTotalAmountInRange(startDate, endDate);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/tong-so-luong-tour-da-dat")
    public ResponseEntity<Integer> getTotalBookedTours(
        @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
        @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {

        Integer totalBookedTours = thongKeService.getTotalBookedTours(startDate, endDate);
        return ResponseEntity.ok(totalBookedTours);
    }

}
