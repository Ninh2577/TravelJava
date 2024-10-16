package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.BienTheTour;
import com.example.Repository.BienTheTourRepository;

import jakarta.transaction.Transactional;

@Service
public class BienTheTourService {

	@Autowired
	private BienTheTourRepository bienTheTourRepository;

	// Phương thức GET thông tin cho người dùng
	public List<BienTheTour> getAllBienTheTour() {
		return bienTheTourRepository.findAll();
	}

	// Phương thức POST thêm biến thể tour
	public BienTheTour addBienTheTour(BienTheTour bienTheTour) {
		return bienTheTourRepository.save(bienTheTour);
	}

	// Phương thức cập nhật loại tour
	@Transactional
	public BienTheTour updateBienTheTour(Integer id, BienTheTour updatedBienTheTour) {
		Optional<BienTheTour> existingBienTheTour = bienTheTourRepository.findById(id);
		if (existingBienTheTour.isPresent()) {
			BienTheTour bienTheTour = existingBienTheTour.get();
			bienTheTour.setTour(updatedBienTheTour.getTour());
			bienTheTour.setHinhAnh(updatedBienTheTour.getHinhAnh());
			bienTheTour.setVideo(updatedBienTheTour.getVideo());
			bienTheTour.setMoTa(updatedBienTheTour.getMoTa());
			return bienTheTourRepository.save(bienTheTour);
		} else {
			return null;
		}
	}

	// Phương thức DELETE : xóa biến thể tour
	@Transactional
	public void deleteBienTheTour(Integer id) {
		Optional<BienTheTour> optionalBienTheNguoiDung = bienTheTourRepository.findById(id);
		if (optionalBienTheNguoiDung.isPresent()) {
			bienTheTourRepository.deleteById(id);

		} else {
			throw new RuntimeException("Biến thể tour không tồn tại " + id);

		}
	}
}