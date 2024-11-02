package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.LichTrinhTour;
import com.example.Repository.LichTrinhTourRepository;

import jakarta.transaction.Transactional;

@Service
public class LichTrinhTourService {

	@Autowired
	private LichTrinhTourRepository lichTrinhTourRepository;
	// Phương thức GET hết thông tin Lịch trinh tour 
	public List<LichTrinhTour> getAllLichTrinhTour(){
		return lichTrinhTourRepository.findAll();
	}
	//Phương thức thêm mới Lịch trình tour 
	public LichTrinhTour addLichTrinhTour(LichTrinhTour lichTrinhTour) {
		return lichTrinhTourRepository.save(lichTrinhTour);
	}
	//Phương thức cập nhật Lịch trinh tour 
	@Transactional
	public LichTrinhTour updatedLichTrinhTour(Integer id , LichTrinhTour updatedLichTrinhTour) {
		Optional<LichTrinhTour> existingLichTrinhTour = lichTrinhTourRepository.findById(id);
		if(existingLichTrinhTour.isPresent()) {
			LichTrinhTour lichTrinhTour = existingLichTrinhTour.get();
			lichTrinhTour.setTour(updatedLichTrinhTour.getTour());
			lichTrinhTour.setTieuDe(updatedLichTrinhTour.getTieuDe());
			lichTrinhTour.setNoiDung(updatedLichTrinhTour.getNoiDung());
			lichTrinhTour.setNgay(updatedLichTrinhTour.getNgay());
			lichTrinhTour.setThoiGianBatDau(updatedLichTrinhTour.getThoiGianBatDau());
			lichTrinhTour.setThoiGianKetThuc(updatedLichTrinhTour.getThoiGianKetThuc());
			lichTrinhTour.setMoTa(updatedLichTrinhTour.getMoTa());
			return lichTrinhTourRepository.save(lichTrinhTour);
		}
		else {
			return null;
		}
	}
	//Phương thức xóa Lịch trình tour 
	@Transactional
	public void deleteLichTrinhTour(Integer id) {
		Optional<LichTrinhTour> optionalLichTrinhTour = lichTrinhTourRepository.findById(id);
		if(optionalLichTrinhTour.isPresent()) {
			lichTrinhTourRepository.deleteById(id);
		}else {
			throw new RuntimeException("Lịch Trình Tour không tồn tại với ID: " +id);
		}
	}
	 public List<LichTrinhTour> getLichTrinhTourByTourId(Integer tourId) {
	        return lichTrinhTourRepository.findByTourId(tourId);
	    }

}
