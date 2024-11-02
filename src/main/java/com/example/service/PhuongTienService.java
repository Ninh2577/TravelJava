package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.PhuongTien;
import com.example.Repository.PhuongTienRepository;

import jakarta.transaction.Transactional;

@Service
public class PhuongTienService {

	@Autowired
	private PhuongTienRepository phuongTienRepository;
	
	//Get Phương thức thông tin loại tour
	public List<PhuongTien> getAllPhuongTiens(){
		return phuongTienRepository.findAll();
	}
	//Phương thức thêm phương tiện mới
	public PhuongTien addPhuongTien(PhuongTien phuongTien) {
		return phuongTienRepository.save(phuongTien);
	}
	//Phương thức cập nhật phương tiện mới
	@Transactional
	public PhuongTien updatePhuongTien(Integer id , PhuongTien updatePhuongTien) {
		Optional<PhuongTien> existingPhuongTien = phuongTienRepository.findById(id);
		if(existingPhuongTien.isPresent()) {
			PhuongTien phuongTien = existingPhuongTien.get();
			phuongTien.setTenPhuongTien(updatePhuongTien.getTenPhuongTien());
			return phuongTienRepository.save(phuongTien);
		}else {
			return null;
		}
	}
	//Phương thức xóa Phương tiện mới
	@Transactional
	public void deletePhuongTien(Integer id) {
		Optional<PhuongTien> optionalPhuongTien = phuongTienRepository.findById(id);
		if(optionalPhuongTien.isPresent()) {
			phuongTienRepository.deleteById(id);
		}else {
			throw new RuntimeException("Phương Tiện không tồn tại với ID: " +id);
		}
	}
}
