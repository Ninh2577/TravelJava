package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.GioHangDanhSachNguoiDiCung;
import com.example.Repository.GioHangDanhSachNguoiDiCungRepository;

@Service
public class GioHangDanhSachNguoiDiCungService {

	@Autowired
	private GioHangDanhSachNguoiDiCungRepository gioHangDanhSachNguoiDiCungRepository;
	
	public List<GioHangDanhSachNguoiDiCung> getAllGioHangDanhSachNguoiDiCung(){
		return gioHangDanhSachNguoiDiCungRepository.findAll();
	}
 }
