package com.example.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.DoanhThuTheoTour;
import com.example.DTO.SoLuongNguoiDiTour;
import com.example.DTO.SoLuongTourDaDatDTO;
import com.example.Repository.HoaDonRepository;
import com.example.service.ThongKeService;

@RestController
@RequestMapping("/api/thongke")
@CrossOrigin(origins = "http://localhost:3000") // Cấu hình cho frontend

public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;

    // @GetMapping("/doanh-thu")
    // public List<Object[]> getRevenueByDateRange(@RequestParam("startDate") String
    // startDateStr,
    // @RequestParam("endDate") String endDateStr) throws Exception {
    // // Chuyển đổi chuỗi ngày tháng sang đối tượng Date
    // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    // Date startDate = sdf.parse(startDateStr);
    // Date endDate = sdf.parse(endDateStr);
    // System.out.println("Doanh thu trong khoảng thời gian từ " + startDateStr + "
    // đến " + endDateStr + ":");

    // // Truy vấn doanh thu
    // return hoaDonRepository.findRevenueByDateRange(startDate, endDate);
    // }

    // @GetMapping("/tongSoTourDuocDat")
    // public List<DoanhThuDTO> getTourPeopleCount(@RequestParam("startDate") String
    // startDateStr,
    // @RequestParam("endDate") String endDateStr) throws Exception {
    // // Chuyển đổi chuỗi ngày tháng sang đối tượng Date
    // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    // Date startDate = sdf.parse(startDateStr);
    // Date endDate = sdf.parse(endDateStr);
    // System.out.println("Doanh thu trong khoảng thời gian từ " + startDateStr + "
    // đến " + endDateStr + ":");

    // // Truy vấn doanh thu
    // return hoaDonRepository.findTourStatisticsByDateRange(startDate, endDate);
    // }

    // @GetMapping("/TongSoNguoiThamGiaTour")
    // public List<SoLuongNguoiDiTour>
    // getTourPeopleCount23(@RequestParam("startDate") String startDateStr,
    // @RequestParam("endDate") String endDateStr) throws Exception {
    // // Chuyển đổi chuỗi ngày tháng sang đối tượng Date
    // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    // Date startDate = sdf.parse(startDateStr);
    // Date endDate = sdf.parse(endDateStr);
    // System.out.println("Doanh thu trong khoảng thời gian từ " + startDateStr + "
    // đến " + endDateStr + ":");

    // // Truy vấn doanh thu
    // return hoaDonRepository.findTourStatisticsByDateRange2Dtos(startDate,
    // endDate);
    // }

    // @GetMapping("/doanhThuTheoTour")
    // public List<DoanhThuTheoTour>
    // getTourPeopleCount232(@RequestParam("startDate") String startDateStr,
    // @RequestParam("endDate") String endDateStr) throws Exception {
    // // Chuyển đổi chuỗi ngày tháng sang đối tượng Date
    // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    // Date startDate = sdf.parse(startDateStr);
    // Date endDate = sdf.parse(endDateStr);
    // System.out.println("Doanh thu trong khoảng thời gian từ " + startDateStr + "
    // đến " + endDateStr + ":");

    // // Truy vấn doanh thu
    // return hoaDonRepository.findTourRevenueByDateRange(startDate, endDate);
    // }
    // API thống kê tổng doanh thu theo thời gian
    @GetMapping("/tong-doanh-thu")
    public List<Object[]> thongKeTongDoanhThuTheoTG(
            @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
            @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
        return thongKeService.thongKeTongDoanhThuTheoTG(ngayBatDau, ngayKetThuc);
    }

    // API thống kê tổng số lượng tour được đặt
    @GetMapping("/so-luong-dat-tour")
    public List<SoLuongTourDaDatDTO> thongKeTourTheoKhoangThoiGian(
            @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
            @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
        return thongKeService.thongKeTourTheoKhoangThoiGian(ngayBatDau, ngayKetThuc);
    }

    // API thống kê số lượng người tham gia mỗi tour
    @GetMapping("/so-luong-nguoi-tham-gia")
    public List<SoLuongNguoiDiTour> thongKeSoLuongNguoiDiTourTheoKhoangThoiGian(
            @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
            @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
        return thongKeService.thongKeSoLuongNguoiDiTourTheoKhoangThoiGian(ngayBatDau, ngayKetThuc);
    }

    // API thống kê doanh thu theo tour
    @GetMapping("/doanh-thu-theo-tour")
    public List<DoanhThuTheoTour> thongKeDoanhThuTheoTourTrongKhoangThoiGian(
            @RequestParam("ngayBatDau") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayBatDau,
            @RequestParam("ngayKetThuc") @DateTimeFormat(pattern = "dd/MM/yyyy") Date ngayKetThuc) {
        return thongKeService.thongKeDoanhThuTheoTourTrongKhoangThoiGian(ngayBatDau, ngayKetThuc);
    }

}
