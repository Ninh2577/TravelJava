package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.DiaDiem;
import com.example.Repository.DiaDiemRepository;

import jakarta.transaction.Transactional;

@Service
public class DiaDiemService {
	@Autowired
	private DiaDiemRepository diaDiemRepository;

	// Phương thức GET thông tin người dùng
	public List<DiaDiem> getAllDiaDiem() {
		return diaDiemRepository.findAll();
	}
	
	//Phương thức thêm địa điểm
	public DiaDiem addDiaDiem(DiaDiem diaDiem) {
		return diaDiemRepository.save(diaDiem);
	}
	
	//Phương thức cập nhật địa điểm 
	@Transactional
	public DiaDiem updateDiaDiem(Integer id, DiaDiem updateDiaDiem) {
		Optional<DiaDiem> existingDiaDiem = diaDiemRepository.findById(id);
		if(existingDiaDiem.isPresent()) {
			DiaDiem diaDiem = existingDiaDiem.get();
			diaDiem.setTour(updateDiaDiem.getTour());
			diaDiem.setViTri(updateDiaDiem.getViTri());
			diaDiem.setTinh(updateDiaDiem.getTinh());
			diaDiem.setQuan(updateDiaDiem.getQuan());
			diaDiem.setPhuong(updateDiaDiem.getPhuong());
			diaDiem.setMoTa(updateDiaDiem.getMoTa());
			return diaDiemRepository.save(diaDiem);
		}
		return null;
	}
	
	//Xóa địa điểm
	@Transactional
	public void deleteDiaDiem(Integer id) {
		Optional<DiaDiem> optionalDiaDiem = diaDiemRepository.findById(id);
		if(optionalDiaDiem.isPresent()) {
			diaDiemRepository.deleteById(id);
		}else {
			throw new RuntimeException("Địa điểm không tôn tại" +id);
		}
	}
}
