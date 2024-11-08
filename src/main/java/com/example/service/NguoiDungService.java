package com.example.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.NguoiDung;
import com.example.Repository.NguoiDungRepository;

import jakarta.transaction.Transactional;

@Service
public class NguoiDungService {
	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	// Phương thức GET hết thông tin người dùng
	public List<NguoiDung> getAllNguoiDungs() {
		return nguoiDungRepository.findAll();
	}

	// Lấy danh sách tất cả người dùng
	public List<NguoiDung> getNguoiDungByVaiTroId(Integer vaiTroId) {
		return nguoiDungRepository.findByVaiTroId(vaiTroId);
	}


	// Phương thức thêm Người Dùng mới
	public NguoiDung addNguoiDung(NguoiDung nguoiDung) {
		 if (nguoiDung.getNamSinh() != null) {
		        // Chuyển đổi từ Date sang LocalDate
		        LocalDate birthDate = nguoiDung.getNamSinh().toInstant()
		                                .atZone(ZoneId.systemDefault())
		                                .toLocalDate();
		        LocalDate currentDate = LocalDate.now();
		        
		        // Tính tuổi chính xác bằng cách sử dụng Period.between
		        int age = Period.between(birthDate, currentDate).getYears();
		        
		        nguoiDung.setTuoi(age); // Gán tuổi đã tính vào đối tượng NguoiDung
		    }
		return nguoiDungRepository.save(nguoiDung); // Lưu Người Dùng mới vào database
	}

	public Optional<NguoiDung> getNguoiDungById(Integer id) {
		return nguoiDungRepository.findById(id);
	}

	//Phương thức cập nhật Người Dùng với ID
	@Transactional
	public NguoiDung updateNguoiDung(Integer id, NguoiDung updatedNguoiDung) {
		Optional<NguoiDung> existingNguoiDung = nguoiDungRepository.findById(id);
		if (existingNguoiDung.isPresent()) {
			NguoiDung nguoiDung = existingNguoiDung.get();
			// Cập nhật các thuộc tính
						nguoiDung.setHoTen(updatedNguoiDung.getHoTen());
						nguoiDung.setEmail(updatedNguoiDung.getEmail());
						nguoiDung.setSoDienThoai(updatedNguoiDung.getSoDienThoai());
						nguoiDung.setDiaChi(updatedNguoiDung.getDiaChi());
						  // Retain existing password if not provided in the updatedNguoiDung
				        if (updatedNguoiDung.getMatKhau() != null && !updatedNguoiDung.getMatKhau().isEmpty()) {
				            nguoiDung.setMatKhau(updatedNguoiDung.getMatKhau());
				        }nguoiDung.setHinhAnh(updatedNguoiDung.getHinhAnh());
						nguoiDung.setGioiTinh(updatedNguoiDung.isGioiTinh());
						nguoiDung.setTuoi(updatedNguoiDung.getTuoi()); // Cập nhật tuổi trong cơ sở dữ liệu
						nguoiDung.setVaiTro(updatedNguoiDung.getVaiTro());
			return nguoiDungRepository.save(nguoiDung);
		} else {
			throw new RuntimeException("Người dùng không tồn tại với ID: " +id);
		}
	}

	// Xóa Người Dùng
	@Transactional
	public void deleteNguoiDung(Integer id) {
		Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(id);
		if (optionalNguoiDung.isPresent()) {
			nguoiDungRepository.deleteById(id); // Delete Người Dùng by ID
		} else {
			throw new RuntimeException("Người dùng không tồn tại với ID: " + id);
		}
	}
	public List<NguoiDung> searchNguoiDung(String hoTen, String soDienThoai, String email, String diaChi,
			Integer tuoi) {
		return nguoiDungRepository.searchNguoiDungByFields(2, hoTen, soDienThoai, email, diaChi, tuoi);
	}
}