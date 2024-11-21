package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.DanhSachNguoiDiCungDTO;
import com.example.Entity.DanhSachNguoiDiCung;
import com.example.Repository.DanhSachNguoiDiCungRepository;

@Service
public class DanhSachNguoiDiCungService {

	@Autowired
	private DanhSachNguoiDiCungRepository danhSachNguoiDiCungRepository;
	
	//GET Phương thức Danh sách người đi cùng 
	public List<DanhSachNguoiDiCung> getAllDanhSachNguoiDiCung(){
		return danhSachNguoiDiCungRepository.findAll();
	}

	public List<DanhSachNguoiDiCungDTO> getDanhSachNguoiDiCung(Integer chiTietHoaDonId, Integer userId) {
        return danhSachNguoiDiCungRepository.findNguoiDiCungByChiTietHoaDonIdAndUserId(chiTietHoaDonId, userId);
    }
}
