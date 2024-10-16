package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entity.Tour;
import com.example.Repository.TourRepository;
import jakarta.transaction.Transactional;

@Service
public class TourService {

	@Autowired
	private TourRepository tourRepository;

	// Phương thức GET hết thông tin người dùng
	public List<Tour> getAllTours() {
		return tourRepository.findAll();
	}

	// Phương thức thêm tour mới
	public Tour addTour(Tour tour) {
		return tourRepository.save(tour); // Lưu tour mới vào database
	}

	public Optional<Tour> getTourById(Integer id) {
		return tourRepository.findById(id);
	}

	@Transactional
	public Tour updateTour(Integer id, Tour updatedTour) {
		Optional<Tour> existingTour = tourRepository.findById(id);
		if (existingTour.isPresent()) {
			Tour tour = existingTour.get();
			// Update fields
			tour.setTenTour(updatedTour.getTenTour());
			tour.setGiaTour(updatedTour.getGiaTour());
			tour.setNgayBatDau(updatedTour.getNgayBatDau());
			tour.setNgayKetThuc(updatedTour.getNgayKetThuc());
			tour.setSoTour(updatedTour.getSoTour());
			tour.setSoLuongNguoi(updatedTour.getSoLuongNguoi());
			tour.setHinhAnh(updatedTour.getHinhAnh());
			tour.setVideo(updatedTour.getVideo());
			tour.setMoTa(updatedTour.getMoTa());
			tour.setLoaiTour(updatedTour.getLoaiTour());
			 if (tour.getSoTour() > 0) {
			        tour.setTrangThai(true); // "Còn"
			    } else {
			        tour.setTrangThai(false); // "Hết"
			    }
//			tour.setTrangThai(updatedTour.isTrangThai());	
			return tourRepository.save(tour);
		} else {
			return null;
		}
	}

	// Xóa Tour
	@Transactional
	public void deleteTour(Integer id) {
		Optional<Tour> optionalNguoiDung = tourRepository.findById(id);
		if (optionalNguoiDung.isPresent()) {
			tourRepository.deleteById(id); // Delete user by ID
		} else {
			throw new RuntimeException("Người dùng không tồn tại với ID: " + id);
		}
	}
}