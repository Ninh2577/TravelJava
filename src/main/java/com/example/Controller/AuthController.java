package com.example.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.NguoiDung;
import com.example.Utils.JwtUtil;
import com.example.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody NguoiDung loginRequest,
            HttpServletResponse response) {
        try {
            // Xác thực người dùng
            NguoiDung nguoiDung = authService.login(loginRequest.getEmail(), loginRequest.getMatKhau());

            // Tạo JWT token
            String token = jwtUtil.generateToken(nguoiDung);

            // Lưu tokezzzn vào cookie
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true); // Bảo mật token không bị truy cập bởi JavaScript
            cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
            cookie.setMaxAge(60 * 60 * 10); // 10 giờ
            cookie.setSecure(true); // Đảm bảo cookie chỉ gửi qua HTTPS
            response.addCookie(cookie);

            // Tạo đối tượng response với token và thông báo thành công
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Đăng nhập thành công!");
            responseBody.put("token", token); // Trả về token trong phản hồi
            responseBody.put("role", nguoiDung.getVaiTro().getVaiTro()); // Thêm vai trò vào phản hồi
            responseBody.put("hoTen", nguoiDung.getHoTen());
            responseBody.put("email", nguoiDung.getEmail());
            responseBody.put("diaChi", nguoiDung.getDiaChi());

            // Trả về phản hồi thành công với mã 200 (OK)
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            // Trả về phản hồi lỗi với mã 400 (Bad Request)
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Đăng nhập thất bại: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
