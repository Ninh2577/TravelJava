package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.BaiViet;
import com.example.Repository.BaiVietRepository;

@Service
public class BaiVietService {

    @Autowired
    private BaiVietRepository baiVietRepository;

    public List<BaiViet> getBaiViet() {
        return baiVietRepository.findAll(); // Lấy tất cả bài viết từ repository
    }
    // Phương thức lấy bài viết theo ID
    public Optional<BaiViet> getBaiVietById(Integer id) {
        return baiVietRepository.findById(id);
    }
}
