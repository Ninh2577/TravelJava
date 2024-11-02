package com.example.service;

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

//		tatws tam thoi ma hoa
//		if (!passwordEncoder.matches(password, nguoiDung.getMatKhau())) {
//			throw new Exception("Email hoặc mật khẩu không đúng");
//		}
		 if (!password.equals(nguoiDung.getMatKhau())) {  // So sánh trực tiếp mật khẩu không mã hóa
		        throw new Exception("Email hoặc mật khẩu không đúng");
		    }

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
}
