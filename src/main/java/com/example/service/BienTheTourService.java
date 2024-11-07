package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.TourDetailsDTO;
import com.example.Entity.BienTheTour;
import com.example.Repository.BienTheTourRepository;

import jakarta.transaction.Transactional;

@Service
public class BienTheTourService {

	@Autowired
	private BienTheTourRepository bienTheTourRepository;

	public List<BienTheTour> getAllBienTheTour() {
		return bienTheTourRepository.findAll();
	}

	public BienTheTour addBienTheTour(BienTheTour bienTheTour) {
		return bienTheTourRepository.save(bienTheTour);
	}

	@Transactional
	public BienTheTour updatedBienTheTour(Integer id, BienTheTour updatedBienTheTour) {
		Optional<BienTheTour> existingBientheTour = bienTheTourRepository.findById(id);
		if (existingBientheTour.isPresent()) {
			BienTheTour bienTheTour = existingBientheTour.get();
			bienTheTour.setHotels(updatedBienTheTour.getHotels());
			bienTheTour.setPhuongTien(updatedBienTheTour.getPhuongTien());
			bienTheTour.setGiamGia(updatedBienTheTour.getGiamGia());
			bienTheTour.setGiaNguoiLon(updatedBienTheTour.getGiaNguoiLon());
			bienTheTour.setGiaTreEm(updatedBienTheTour.getGiaTreEm());
			bienTheTour.setTour(updatedBienTheTour.getTour());
			bienTheTour.setNgayBatDau(updatedBienTheTour.getNgayBatDau());
			bienTheTour.setNgayKetThuc(updatedBienTheTour.getNgayKetThuc());
			bienTheTour.setSoLuongCon(updatedBienTheTour.getSoLuongCon());
			bienTheTour.setSoLuongTong(updatedBienTheTour.getSoLuongTong());
			bienTheTour.setGhiChu(updatedBienTheTour.getGhiChu());
			bienTheTour.setMaTour(updatedBienTheTour.getMaTour());
			return bienTheTourRepository.save(bienTheTour);
		} else {
			return null;
		}
	}

	@Transactional
	public void deleteBienTheTour(Integer id) {
		Optional<BienTheTour> optionalBienTheTour = bienTheTourRepository.findById(id);
		if (optionalBienTheTour.isPresent()) {
			bienTheTourRepository.deleteById(id);
		} else {
			throw new RuntimeException("Biến thể tour không tồn tại với ID: " + id);
		}
	}

	public List<BienTheTour> getBienTheTourByTourId(Integer tourId) {
		return bienTheTourRepository.findByTourId(tourId);
	}
	
}
