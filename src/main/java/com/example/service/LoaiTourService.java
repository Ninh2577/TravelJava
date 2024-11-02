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
	
	//GET Phương thức thông tin loại tour
	public List<LoaiTour> getAllLoaiTours(){
		return loaiTourRepository.findAll();
	}
	//Phương thức thêm loại tour mới 
	public LoaiTour addLoaiTour(LoaiTour loaiTour) {
		return loaiTourRepository.save(loaiTour);
	}
	//Phương thức cập nhật loại tour 
	@Transactional
	public LoaiTour updateLoaiTour(Integer id , LoaiTour updateLoaiTour) {
		Optional<LoaiTour> existingLoaiTour = loaiTourRepository.findById(id);
		if(existingLoaiTour.isPresent()) {
			LoaiTour loaiTour = existingLoaiTour.get();
			loaiTour.setLoaiTour(updateLoaiTour.getLoaiTour());
		return loaiTourRepository.save(loaiTour);
		}
		else {
			return null;
		}
	}
		// Xóa loại tour
		@Transactional
		public void deleteLoaiTour(Integer id) {
			Optional<LoaiTour> optionalLoaiTour = loaiTourRepository.findById(id);
			if(optionalLoaiTour.isPresent()) {
				loaiTourRepository.deleteById(id);
			}else {
				throw new RuntimeException("Loại tour không tồn tại với ID: " +id);
			}
		}
	}
