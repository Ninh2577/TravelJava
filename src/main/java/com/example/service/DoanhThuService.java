package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.DTO.DoanhThuDTO;
import com.example.Repository.DoanhThuRepository;
import org.springframework.stereotype.Service;

@Service
public class DoanhThuService {
    @Autowired
    private DoanhThuRepository doanhThuRepository;

    public List<Integer> getAllDistinctYears() {
        return doanhThuRepository.findAllDistinctYears();
    }

    // Lấy doanh thu theo năm
    public List<DoanhThuDTO> getDoanhThuByYear(int startYear, int endYear) {

        return doanhThuRepository.findDoanhThuByYearRange(startYear, endYear);
    }
}
