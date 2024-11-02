package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.HoaDon;
import com.example.Repository.HoaDonRepository;

@Service
public class HoaDonService {

	@Autowired
	private HoaDonRepository hoaDonRepository;
	
	// GET phương thức Hóa đơn 
	public List<HoaDon> getAllHoaDon(){
		return hoaDonRepository.findAll();
	}
}
