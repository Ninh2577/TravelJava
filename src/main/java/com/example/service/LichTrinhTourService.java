package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.LichTrinhTour;
import com.example.Repository.LichTrinhTourRepository;

import jakarta.transaction.Transactional;

@Service
public class LichTrinhTourService {
	
	@Autowired
	private LichTrinhTourRepository lichTrinhTourRepository;
	
	//Phương thức GET hết thông tin lịch trình tour
	public List<LichTrinhTour> getAllLichTrinhTours(){
		return lichTrinhTourRepository.findAll();
	}
	// Phương thức thêm lịch trình tour mới
	public LichTrinhTour addLichTrinhTour(LichTrinhTour lichTrinhTour) {
		return lichTrinhTourRepository.save(lichTrinhTour);
	}
	// Phương thức cập nhật Lịch trình tour 
	@Transactional
	public LichTrinhTour updateLichTrinhTour(Integer id , LichTrinhTour updateLichTrinhTour) {
		Optional<LichTrinhTour> existingLichTrinhTour = lichTrinhTourRepository.findById(id);
		if(existingLichTrinhTour.isPresent()) {
			LichTrinhTour lichTrinhTour = existingLichTrinhTour.get();
			lichTrinhTour.setTour(updateLichTrinhTour.getTour());
			lichTrinhTour.setTieuDe(updateLichTrinhTour.getTieuDe());
			lichTrinhTour.setNoiDung(updateLichTrinhTour.getNoiDung());
			lichTrinhTour.setNgay(updateLichTrinhTour.getNgay());
			return lichTrinhTourRepository.save(lichTrinhTour);
			
		}else {
			return null;
		}
	}
	@Transactional
	public void deleteLichTrinhTour(Integer id) {
		Optional<LichTrinhTour> optionalLichTrinhTour = lichTrinhTourRepository.findById(id);
		if(optionalLichTrinhTour.isPresent()) {
			lichTrinhTourRepository.deleteById(id);
		}else {
			throw new RuntimeException("Lịch trình tour không tồn tại với ID: " + id);
		}
	}
}
