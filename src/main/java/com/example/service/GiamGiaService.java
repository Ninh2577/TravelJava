package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.GiamGia;
import com.example.Repository.GiamGiaRepository;

import jakarta.transaction.Transactional;

@Service
public class GiamGiaService {

	@Autowired
	private GiamGiaRepository giamGiaRepository;
	
	//Phương thức GET hết thông tin Giảm giá
	public List<GiamGia> getAllGiamGias(){
		return giamGiaRepository.findAll();
	}
	//Phương thức thêm thông tin Giá tour
	public GiamGia addGiamGia (GiamGia giamGia) {
		return giamGiaRepository.save(giamGia);
	}
	//Phương thức cập nhật thông tin Giá Tour
	@Transactional
	public GiamGia updatedGiamGia(Integer id , GiamGia updatedGiamGia) {
		Optional<GiamGia> existingGiamGia = giamGiaRepository.findById(id);
		if(existingGiamGia.isPresent()) {
			GiamGia giamGia = existingGiamGia.get();
			giamGia.setTour(updatedGiamGia.getTour());
			giamGia.setNguoiDung(updatedGiamGia.getNguoiDung());
			giamGia.setTieuDe(updatedGiamGia.getTieuDe());
			giamGia.setMoTa(updatedGiamGia.getMoTa());
			giamGia.setNgayBatDau(updatedGiamGia.getNgayBatDau());
			giamGia.setNgayKetThuc(updatedGiamGia.getNgayKetThuc());
			giamGia.setNoiDung(updatedGiamGia.getNoiDung());
			giamGia.setHinhAnh(updatedGiamGia.getHinhAnh());
			giamGia.setPhanTram(updatedGiamGia.getPhanTram());
			giamGia.setSoLuong(updatedGiamGia.getSoLuong());
			return giamGiaRepository.save(giamGia);
		}else {
			return null;
		}
	}
	//Phương thức xóa Thông tin Giá tour
	@Transactional
	public void deleteGiamGia(Integer id) {
		Optional<GiamGia> optionalGiamGia = giamGiaRepository.findById(id);
		if(optionalGiamGia.isPresent()) {
			giamGiaRepository.deleteById(id);
		}else {
			throw new RuntimeException("Giảm giá không tồn tại với ID: " +id);
		}
	}
}
