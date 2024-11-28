package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.GioHangNguoiDiCungDTO;
import com.example.Entity.ChiTietGioHang;
import com.example.Entity.GioHangDanhSachNguoiDiCung;
import com.example.Repository.GioHangDanhSachNguoiDiCungRepository;

import jakarta.transaction.Transactional;

@Service
public class GioHangDanhSachNguoiDiCungService {

	@Autowired
	private GioHangDanhSachNguoiDiCungRepository gioHangDanhSachNguoiDiCungRepository;

	public List<GioHangDanhSachNguoiDiCung> getAllGioHangDanhSachNguoiDiCung() {
		return gioHangDanhSachNguoiDiCungRepository.findAll();
	}

	// Phương thức thêm Giỏ hàng danh sách người dùng
	public GioHangDanhSachNguoiDiCung addGioHangDanhSachNguoiDiCung(
			GioHangDanhSachNguoiDiCung gioHangDanhSachNguoiDiCung) {
		return gioHangDanhSachNguoiDiCungRepository.save(gioHangDanhSachNguoiDiCung);
	}

	public List<GioHangDanhSachNguoiDiCung> getGioHangDanhSachNguoiDiCungByChiTietGioHangId(
			Integer idGioHangDanhSachNguoiDiCung) {
		return gioHangDanhSachNguoiDiCungRepository.findByGioHangDanhSachNguoiDiCungId(idGioHangDanhSachNguoiDiCung);
	}

	// Phương thức gọi repository để lấy danh sách người đi cùng
	public List<GioHangNguoiDiCungDTO> getDanhSachNguoiDiCung(Integer idChiTietGioHang) {
		return gioHangDanhSachNguoiDiCungRepository.findDanhSachNguoiDiCung(idChiTietGioHang);
	}




	// -------------------------------------------------
	@Transactional
	public void deleteByChiTietGioHangId(Long chiTietGioHangId) {
        gioHangDanhSachNguoiDiCungRepository.deleteByChiTietGioHangId(chiTietGioHangId);
    }

	// -------------------------------------------------
	@Transactional
	public void delete(Integer id) {
        gioHangDanhSachNguoiDiCungRepository.deleteById(id);
    }

	@Transactional
	public void deleteMultiple(List<Integer> ids) {
		for (Integer id : ids) {
			// Xử lý xóa từng ID
			gioHangDanhSachNguoiDiCungRepository.deleteById(id); 
		}
	}
	

	// ---------------------------
	 // Cập nhật danh sách người đi cùng
	 @Transactional
	 public List<GioHangDanhSachNguoiDiCung> updateGioHangDanhSachNguoiDiCung(
			 List<GioHangDanhSachNguoiDiCung> danhSachNguoiDiCung) {
		 for (GioHangDanhSachNguoiDiCung item : danhSachNguoiDiCung) {
			 GioHangDanhSachNguoiDiCung existingItem = gioHangDanhSachNguoiDiCungRepository.findById(item.getId())
					 .orElseThrow(() -> new RuntimeException("Không tìm thấy người đi cùng với ID: " + item.getId()));
			 existingItem.setHoTen(item.getHoTen());
			 existingItem.setEmail(item.getEmail());
			 existingItem.setSoDienThoai(item.getSoDienThoai());
			 existingItem.setNamSinh(item.getNamSinh());
			 // Cập nhật các trường khác nếu cần
			 gioHangDanhSachNguoiDiCungRepository.save(existingItem);
		 }
		 return danhSachNguoiDiCung;
	 }
}
