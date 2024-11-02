package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.DanhMucTour;
import com.example.Repository.DanhMucTourRepository;

import jakarta.transaction.Transactional;

@Service
public class DanhMucTourService {

	@Autowired
	private DanhMucTourRepository danhMucTourRepository;
	//GET danh sách Danh Mục Tour
	public List<DanhMucTour> getAllDanhMucTour(){
		return danhMucTourRepository.findAll();
	}
	//POST danh sách Danh Mục Tour
	public DanhMucTour addDanhMucTour(DanhMucTour danhMucTour) {
		return danhMucTourRepository.save(danhMucTour);
	}
	//PUT danh sách Danh Mục Tour
	@Transactional
	public DanhMucTour updateDanhMucTour(Integer id , DanhMucTour updateDanhMucTour) {
		Optional<DanhMucTour> existingDanhMucTour = danhMucTourRepository.findById(id);
		if(existingDanhMucTour.isPresent()) {
			DanhMucTour danhMucTour = existingDanhMucTour.get();
			danhMucTour.setTenDanhMuc(updateDanhMucTour.getTenDanhMuc());
			return danhMucTourRepository.save(danhMucTour);
		}
		return null;
	}
	//DELETE danh sách Danh Mục Tour
	@Transactional
	public void deleteDanhMucTour(Integer id) {
		Optional<DanhMucTour> optionalDanhMucTour = danhMucTourRepository.findById(id);
		if(optionalDanhMucTour.isPresent()) {
			danhMucTourRepository.deleteById(id);
		}else {
			throw new RuntimeException("Danh Mục Tour không tồn tại với ID:" + id);
		}
	}
}
