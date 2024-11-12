package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.ChiTietHoaDon;
import com.example.Repository.ChiTietHoaDonRepository;

@Service
public class ChiTietHoaDonService {
 
	@Autowired
	private ChiTietHoaDonRepository datTourRepository;
	
	//GET phương thức Đặt tour
	public List<ChiTietHoaDon> getAllChiTietHoaDons(){
		return datTourRepository.findAll();
	}
}
