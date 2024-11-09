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

    // Lấy doanh thu theo năm
    public List<DoanhThuDTO> getDoanhThuByYear(int year) {
        if (year < 1) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }
        return doanhThuRepository.findDoanhThuByYear(year);
    }
}
