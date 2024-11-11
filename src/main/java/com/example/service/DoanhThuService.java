package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.DTO.DoanhThuDTO;
import com.example.DTO.DoanhThuNamDTO;
import com.example.DTO.DoanhThuNgayDTO;
import com.example.DTO.DoanhThuThangDTO;
import com.example.Repository.DoanhThuRepository;
import org.springframework.stereotype.Service;

@Service
public class DoanhThuService {
    @Autowired
    private DoanhThuRepository doanhThuRepository;

    //lấy năm đổ cbo
    public List<Integer> getAllDistinctYears() {
        return doanhThuRepository.findAllDistinctYears();
    }

    //doanh thu theo năm 
    public List<DoanhThuNamDTO> getDoanhThuByYear(int startYear, int endYear) {

        return doanhThuRepository.findDoanhThuByYearRange1Dtos(startYear, endYear);
    }

    public List<DoanhThuThangDTO> getDoanhThuByMonth(int year) {
        return doanhThuRepository.findDoanhThuByMonthInYear(year);
    }

    public List<DoanhThuNgayDTO> getDoanhThuByDay(int year, int month) {
        return doanhThuRepository.findDoanhThuByDay(year,month);
    }















    // Lấy doanh thu theo năm
    // public List<DoanhThuDTO> getDoanhThuByYear(int startYear, int endYear) {

    //     return doanhThuRepository.findDoanhThuByYearRange(startYear, endYear);
    // }



 


    public List<DoanhThuNamDTO> getDoanhThuByYear2(int year) {
        return doanhThuRepository.findDoanhThuByYearRangeAndMonth(year);
    }
}
