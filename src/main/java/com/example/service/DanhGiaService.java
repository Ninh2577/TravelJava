package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.DanhGia;
import com.example.Entity.MediaTour;
import com.example.Repository.DanhGiaRepository;

@Service
public class DanhGiaService {

	@Autowired
	private DanhGiaRepository danhGiaRepository;
	//GET Phương thức Đánh giá
	public List<DanhGia> getAllDanhGia(){
		return danhGiaRepository.findAll();
	}
	
	 public List<DanhGia> getDanhGiarByTourId(Integer idTour) {
	        return danhGiaRepository.findByDanhGiaId(idTour);
	    }
}
