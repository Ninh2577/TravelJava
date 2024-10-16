package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entity.LoaiTour;
import com.example.Repository.LoaiTourRepository;
import jakarta.transaction.Transactional;

@Service
public class LoaiTourService {
	@Autowired
	private LoaiTourRepository loaiTourRepository;

	// Phương thức GET hết thông tin người dùng
	public List<LoaiTour> getAllLoaiTours() {
		return loaiTourRepository.findAll();
	}

	// Phương thức thêm loại tour mới
	public LoaiTour addLoaiTour(LoaiTour loaiTour) {
		return loaiTourRepository.save(loaiTour); // Lưu tour mới vào database
	}

	// Phương thức cập nhật loại tour
	@Transactional
	public LoaiTour updateLoaiTour(Integer id, LoaiTour updatedLoaiTour) {
		Optional<LoaiTour> existingLoaiTour = loaiTourRepository.findById(id);
		if (existingLoaiTour.isPresent()) {
			LoaiTour loaiTour = existingLoaiTour.get();
			loaiTour.setLoaiTour(updatedLoaiTour.getLoaiTour());
			loaiTour.setMoTa(updatedLoaiTour.getMoTa());
			return loaiTourRepository.save(loaiTour);
		} else {
			return null;
		}
	}

	// Xóa Loại Tour
	@Transactional
	public void deleteLoaiTour(Integer id) {
		Optional<LoaiTour> optionalNguoiDung = loaiTourRepository.findById(id);
		if (optionalNguoiDung.isPresent()) {
			loaiTourRepository.deleteById(id); // Delete user by ID
		} else {
			throw new RuntimeException("Người dùng không tồn tại với ID: " + id);
		}
	}
}
