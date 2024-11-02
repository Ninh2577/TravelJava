package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.ChiTietGioHang;
import com.example.Repository.ChiTietGioHangRepository;

@Service
public class ChiTietGioHangService {

	@Autowired
	private ChiTietGioHangRepository chiTietGioHangRepository;
	//GET phương thức Chi tiết giỏ hàng
	public List<ChiTietGioHang> getAllChiTietGioHang(){
		return chiTietGioHangRepository.findAll();
	}
}
