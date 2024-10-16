package com.example.service;

import com.example.Entity.NguoiDung;
import com.example.Repository.NguoiDungRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungService {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	// Lấy danh sách tất cả người dùng
	public List<NguoiDung> getNguoiDungByVaiTroId(Integer vaiTroId) {
		return nguoiDungRepository.findByVaiTroId(vaiTroId);
	}

	// Tìm người dùng theo ID
	public Optional<NguoiDung> getNguoiDungById(Integer id) {
		return nguoiDungRepository.findById(id);
	}

	// Thêm người dùng
	@Transactional
	public NguoiDung addNguoiDung(NguoiDung nguoiDung) {
		return nguoiDungRepository.save(nguoiDung);
	}

	// Cập nhật người dùng
	@Transactional
	public NguoiDung updateNguoiDung(Integer id, NguoiDung updatedNguoiDung) {
		Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(id);
		if (optionalNguoiDung.isPresent()) {
			NguoiDung existingNguoiDung = optionalNguoiDung.get();
			// Cập nhật các thuộc tính
			existingNguoiDung.setHoTen(updatedNguoiDung.getHoTen());
			existingNguoiDung.setEmail(updatedNguoiDung.getEmail());
			existingNguoiDung.setSoDienThoai(updatedNguoiDung.getSoDienThoai());
			existingNguoiDung.setDiaChi(updatedNguoiDung.getDiaChi());
			existingNguoiDung.setGioiTinh(updatedNguoiDung.isGioiTinh());
			existingNguoiDung.setNamSinh(updatedNguoiDung.getNamSinh());
			existingNguoiDung.setCccd(updatedNguoiDung.getCccd());
			existingNguoiDung.setAnhCCCDtruoc(updatedNguoiDung.getAnhCCCDtruoc());
			existingNguoiDung.setAnhCCCDsau(updatedNguoiDung.getAnhCCCDsau());
			existingNguoiDung.setHinhAnh(updatedNguoiDung.getHinhAnh());
			existingNguoiDung.setVaiTro(updatedNguoiDung.getVaiTro());
			existingNguoiDung.setTuoi(updatedNguoiDung.getTuoi()); // Cập nhật tuổi trong cơ sở dữ liệu

			// Lưu người dùng đã cập nhật vào database
			return nguoiDungRepository.save(existingNguoiDung);
		} else {
			throw new RuntimeException("Người dùng không tồn tại với ID: " + id);
		}
	}

	// Xóa người dùng
	@Transactional
	public void deleteNguoiDung(Integer id) {
		Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(id);
		if (optionalNguoiDung.isPresent()) {
			nguoiDungRepository.deleteById(id); // Delete user by ID
		} else {
			throw new RuntimeException("Người dùng không tồn tại với ID: " + id);
		}
	}

	public List<NguoiDung> searchNguoiDung(String hoTen, String soDienThoai, String email, String diaChi,
			Integer tuoi) {
		return nguoiDungRepository.searchNguoiDungByFields(2, hoTen, soDienThoai, email, diaChi, tuoi);
	}
	 public List<NguoiDung> sortNguoiDungByIdAsc() {
	        return nguoiDungRepository.findAllByOrderByIdAsc();
	    }

	    public List<NguoiDung> sortNguoiDungByIdDesc() {
	        return nguoiDungRepository.findAllByOrderByIdDesc();
	    }
}