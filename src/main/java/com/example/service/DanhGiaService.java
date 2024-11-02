package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.DanhGia;
import com.example.Repository.DanhGiaRepository;

@Service
public class DanhGiaService {

	@Autowired
	private DanhGiaRepository danhGiaRepository;
	//GET Phương thức Đánh giá
	public List<DanhGia> getAllDanhGia(){
		return danhGiaRepository.findAll();
	}
}
