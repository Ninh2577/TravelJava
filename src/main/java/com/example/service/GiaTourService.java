package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.GiaTour;
import com.example.Repository.GiaTourRepository;

import jakarta.transaction.Transactional;

@Service
public class GiaTourService {

	@Autowired
	private GiaTourRepository giaTourRepository;
	//GET Phương thức thông tin Giá tour
	public List<GiaTour> getAllGiaTour(){
		return giaTourRepository.findAll();
	}
	//Phương thức thêm Giá tour mới
	public GiaTour addGiaTour(GiaTour giaTour) {
		return giaTourRepository.save(giaTour);
	}
	//Phương thức cập nhật Giá tour
	@Transactional
	public GiaTour updatedGiaTour(Integer id, GiaTour updatedGiaTour) {
		Optional<GiaTour> existingGiaTour = giaTourRepository.findById(id);
		if(existingGiaTour.isPresent()) {
			GiaTour giaTour = existingGiaTour.get();
			giaTour.setGiaNguoiLon(updatedGiaTour.getGiaNguoiLon());
			giaTour.setGiaTreEm(updatedGiaTour.getGiaTreEm());
			return giaTourRepository.save(giaTour);
		}
		else {
			return null;
		}
	}
	//Phương thức xóa Giá tour
	@Transactional
	public void deleteGiaTour(Integer id) {
		Optional<GiaTour> optionalGiaTour = giaTourRepository.findById(id);
		if(optionalGiaTour.isPresent()) {
			 giaTourRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("Giá tour không tồn tại với ID: " + id);
		}
	}
}
