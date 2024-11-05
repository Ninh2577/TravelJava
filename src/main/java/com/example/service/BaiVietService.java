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

	// Phương thức thêm bài viết mới
	public BaiViet addBaiViet(BaiViet baiViet) {
		return baiVietRepository.save(baiViet); // Lưu bài viết vào cơ sở dữ liệu
	}

	// Phương thức cập nhật bài viết
	public BaiViet updateBaiViet(Integer id, BaiViet baiViet) {
		if (baiVietRepository.existsById(id)) {
			baiViet.setId(id);
			return baiVietRepository.save(baiViet); // Lưu bài viết đã cập nhật
		} else {
			throw new RuntimeException("Bài viết không tồn tại với ID: " + id); // Hoặc trả về Optional<BaiViet>
		}
	}

	// Phương thức xóa bài viết
	public void deleteBaiViet(Integer id) {
		baiVietRepository.deleteById(id); // Xóa bài viết theo ID
	}

}
