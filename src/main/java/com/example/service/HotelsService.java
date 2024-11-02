package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Hotels;
import com.example.Repository.HotelsRepository;

import jakarta.transaction.Transactional;

@Service
public class HotelsService {

	@Autowired
	private HotelsRepository hotelsRepository;

	// Phương thức GET hết thông tin Hotels
	public List<Hotels> getAllHotels() {
		return hotelsRepository.findAll();
	}

	// Phương thức thêm Hotels
	public Hotels addHotels(Hotels hotels) {
		return hotelsRepository.save(hotels);
	}

	// Phương thức cập nhật Hotels
	@Transactional
	public Hotels updateHotels(Integer id, Hotels updateHotels) {
		Optional<Hotels> existingHotels = hotelsRepository.findById(id);
		if (existingHotels.isPresent()) {
			Hotels hotels = existingHotels.get();
			hotels.setTenKhachSan(updateHotels.getTenKhachSan());
			hotels.setDanhGiaKhachSan(updateHotels.getDanhGiaKhachSan());
			return hotelsRepository.save(hotels);
		} else {
			return null;
		}
	}

	// Phương thức xóa Hotes
	@Transactional
	public void deleteHotels(Integer id) {
		Optional<Hotels> optionalHotels = hotelsRepository.findById(id);
		if (optionalHotels.isPresent()) {
			hotelsRepository.deleteById(id);
		} else {
			throw new RuntimeException("Hotel không tồn tại với ID: " + id);
		}
	}
}
