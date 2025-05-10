package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.DoanhThuTheoTour;
import com.example.DTO.SoLuongNguoiDiTour;
import com.example.DTO.SoLuongTourDaDatDTO;
import com.example.Repository.ThongKeRepository;

@Service
public class ThongKeService {
    @Autowired
    private ThongKeRepository thongKeRepository;

     // Thống kê tổng doanh thu theo khoản thời gian
    public List<Object[]> thongKeTongDoanhThuTheoTG(Date ngayBatDau, Date ngayKetThuc) {
        return thongKeRepository.ThongKeTongDoanhThuTheoTG(ngayBatDau, ngayKetThuc);
    }

    // Thống kê tổng số lượng tour được đặt trong khoảng thời gian
    // public List<SoLuongTourDaDatDTO> thongKeTourTheoKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {
    //     return thongKeRepository.ThongKeTourTheoKhoangThoiGian(ngayBatDau, ngayKetThuc);
    // }
    public List<Object[]> countBookedToursByDate(Date startDate, Date endDate) {
        return thongKeRepository.countTotalBookedToursByDate(startDate, endDate);
    }

    // Thống kê số lượng người tham gia mỗi tour
    // public List<SoLuongNguoiDiTour> thongKeSoLuongNguoiDiTourTheoKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {
    //     return thongKeRepository.findThongKeSoLuongNguoiDiTourTheoKhoangThoiGian(ngayBatDau, ngayKetThuc);
    // }
    public List<Object[]> thongKeSoLuongNguoiDiTourTheoKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {
        return thongKeRepository.countTotalBookedToursByDateWithTour(ngayBatDau, ngayKetThuc);
    }
    


    // Thống kê doanh thu theo tour
    public List<DoanhThuTheoTour> thongKeDoanhThuTheoTourTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {
        return thongKeRepository.findThongKeDoanhThuTheoTourTrongKhoangThoiGian(ngayBatDau, ngayKetThuc);
    }
    
    public Double calculateTotalAmountInRange(Date startDate, Date endDate) {
        return thongKeRepository.calculateTotalAmountInRange(startDate, endDate); // Gọi query từ repository
    }
 // Phương thức Service để lấy tổng số lượng tour đã đặt
    public Integer getTotalBookedTours(Date startDate, Date endDate) {
        return thongKeRepository.countTotalBookedTours(startDate, endDate);
    }

}
