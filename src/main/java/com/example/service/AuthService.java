package com.example.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Entity.NguoiDung;
import com.example.Repository.NguoiDungRepository;

@Service
public class AuthService {
	@Autowired
	private NguoiDungRepository nguoiDungRepository;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// Phương thức login không cần kiểm tra mã hóa
	public NguoiDung login(String email, String password) throws Exception {
		// Tìm người dùng theo email
		Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findByEmail(email);

		// Kiểm tra nếu không có người dùng hoặc mật khẩu không khớp, ném lỗi
		if (!nguoiDungOpt.isPresent()) {
			throw new Exception("Email không tồn tại");
		}

		NguoiDung nguoiDung = nguoiDungOpt.get(); // Lấy đối tượng NguoiDung từ Optional

		// tatws tam thoi ma hoa
		if (!passwordEncoder.matches(password, nguoiDung.getMatKhau())) {
			throw new Exception("Email hoặc mật khẩu không đúng");
		}
		// if (!password.equals(nguoiDung.getMatKhau())) { // So sánh trực tiếp mật khẩu
		// không mã hóa
		// throw new Exception("Email hoặc mật khẩu không đúng");
		// }

		// Trả về người dùng nếu đăng nhập thành công
		return nguoiDung;
	}

	public void updatePassword(String email, String newPassword) {
		Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findByEmail(email);
		if (optionalNguoiDung.isPresent()) {
			NguoiDung nguoiDung = optionalNguoiDung.get(); // Lấy đối tượng NguoiDung từ Optional
			nguoiDung.setMatKhau(passwordEncoder.encode(newPassword)); // Mã hóa mật khẩu mới
			nguoiDungRepository.save(nguoiDung); // Lưu người dùng đã cập nhật vào cơ sở dữ liệu
		} else {
			throw new RuntimeException("Người dùng không tồn tại");
		}
	}

	public NguoiDung capNhatThongTin(int id, NguoiDung capNhattt) throws Exception {
		Optional<NguoiDung> nguoiDungOptional = nguoiDungRepository.findById(id);
		// Kiểm tra nếu không tìm thấy người dùng
		if (!nguoiDungOptional.isPresent()) {
			throw new Exception("Người dùng không tồn tại!");
		}
		// Lấy đối tượng người dùng hiện tại
		NguoiDung existingUser = nguoiDungOptional.get();

		// Kiểm tra xem email mới có tồn tại trong hệ thống không
		if (!existingUser.getEmail().equals(capNhattt.getEmail())) {
			Optional<NguoiDung> emailExist = nguoiDungRepository.findByEmail(capNhattt.getEmail());
			if (emailExist.isPresent()) {
				throw new Exception("Email này đã tồn tại trong hệ thống!");
			}
		}
		// Cập nhật thông tin người dùng từ đối tượng capNhattt
		existingUser.setHoTen(capNhattt.getHoTen());
		existingUser.setEmail(capNhattt.getEmail());
		existingUser.setSoDienThoai(capNhattt.getSoDienThoai());
		existingUser.setGioiTinh(capNhattt.isGioiTinh());
		existingUser.setNamSinh(capNhattt.getNamSinh());
		existingUser.setDiaChi(capNhattt.getDiaChi());
		// Tính tuổi mới
		if (capNhattt.getNamSinh() != null) {
			Date namSinhDate = capNhattt.getNamSinh(); // lấy ngày sinh kiểu Date
			LocalDate birthDate = namSinhDate.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();

			LocalDate currentDate = LocalDate.now();

			// Tính tuổi chính xác
			int age = Period.between(birthDate, currentDate).getYears();
			existingUser.setTuoi(age); // Gán tuổi đã tính vào đối tượng NguoiDung
		}
		return nguoiDungRepository.save(existingUser);
	}
	
	public NguoiDung capNhatHinhAnh(int id, NguoiDung capNhattt) throws Exception {
		Optional<NguoiDung> nguoiDungOptional = nguoiDungRepository.findById(id);
		// Kiểm tra nếu không tìm thấy người dùng
		if (!nguoiDungOptional.isPresent()) {
			throw new Exception("Người dùng không tồn tại!");
		}
		// Lấy đối tượng người dùng hiện tại
		NguoiDung existingUser = nguoiDungOptional.get();

		// Kiểm tra và cập nhật hình ảnh
		if (capNhattt.getHinhAnh() != null) {
			existingUser.setHinhAnh(capNhattt.getHinhAnh()); // Cập nhật trường avatar mới
		}

		// Lưu lại người dùng sau khi cập nhật hình ảnh
		return nguoiDungRepository.save(existingUser);
	}
}
