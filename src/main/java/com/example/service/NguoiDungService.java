package com.example.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Entity.NguoiDung;
import com.example.Repository.NguoiDungRepository;

import jakarta.transaction.Transactional;

@Service
public class NguoiDungService implements UserDetailsService {
	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Mã hóa mật khẩu


	public boolean existsByEmail(String email) {
		return nguoiDungRepository.existsByEmail(email);
	}

	// Phương thức GET hết thông tin người dùng
	public List<NguoiDung> getAllNguoiDungs() {
		return nguoiDungRepository.findAll();
	}

	// Lấy danh sách tất cả người dùng
	public List<NguoiDung> getNguoiDungByVaiTroId(Integer vaiTroId) {
		return nguoiDungRepository.findByVaiTroId(vaiTroId);
	}

	// dangKy
	// Thêm người dùng
	@Transactional
	public NguoiDung addNguoiDungke(NguoiDung nguoiDung) {
		// Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu

		// tắt mã hóa tạm thới
		String hashedPassword = passwordEncoder.encode(nguoiDung.getMatKhau());
		nguoiDung.setMatKhau(hashedPassword); // Cập nhật mật khẩu đã mã hóa

		if (nguoiDung.getNamSinh() != null) {
			Date namSinhDate = nguoiDung.getNamSinh(); // lấy ngày sinh kiểu Date
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String namSinhString = formatter.format(namSinhDate); // Chuyển đổi Date thành String
			System.out.println("Ngày sinh định dạng: " + namSinhString);

			// Nếu bạn cần cập nhật giá trị trở lại trong đối tượng nguoiDung
			nguoiDung.setNamSinh(namSinhDate); // Nếu bạn có thuộc tính namSinhString trong NguoiDung
		}
		return nguoiDungRepository.save(nguoiDung);
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

	// Phương thức cập nhật Người Dùng với ID
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
			}
			nguoiDung.setHinhAnh(updatedNguoiDung.getHinhAnh());
			nguoiDung.setGioiTinh(updatedNguoiDung.isGioiTinh());
			nguoiDung.setTuoi(updatedNguoiDung.getTuoi()); // Cập nhật tuổi trong cơ sở dữ liệu
			nguoiDung.setVaiTro(updatedNguoiDung.getVaiTro());
			return nguoiDungRepository.save(nguoiDung);
		} else {
			throw new RuntimeException("Người dùng không tồn tại với ID: " + id);
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findByEmail(email);
		if (optionalNguoiDung.isPresent()) {
			NguoiDung nguoiDung = optionalNguoiDung.get();
			// Đảm bảo vai trò được trả về chính xác
			System.out.println("User role: " + nguoiDung.getVaiTro().getVaiTro());
			return nguoiDung; // NguoiDung implement UserDetails
		} else {
			throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email);
		}
	}
}