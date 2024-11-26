package com.example.service;

import com.example.Entity.ChucNang;
import com.example.Repository.ChucNangRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChucNangService {

    @Autowired
    private ChucNangRepository chucNangRepository;

    // Lấy chức năng theo ID
    public ChucNang getChucNangById(Integer id) {
        return chucNangRepository.findById(id).orElse(null);
    }

    // Lấy tất cả chức năng
    public List<ChucNang> getAllChucNang() {
        return chucNangRepository.findAll();
    }
}
