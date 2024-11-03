package com.example.Utils;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.Entity.NguoiDung;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY; // Khóa bí mật từ cấu hình

    // Tạo token cho người dùng
    public String generateToken(NguoiDung user) {
        // Khởi tạo các thông tin cho token
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", Collections.singletonList(user.getVaiTro().getVaiTro())); // Thêm vai trò vào claims
        System.out.println("Roles stored in token: " + Collections.singletonList(user.getVaiTro().getVaiTro()));

        System.out.println("User role: " + user.getVaiTro().getVaiTro());
        // Tạo token với thông tin email và thời gian hết hạn
        return Jwts.builder()
                .setClaims(claims) // Thêm các thông tin tùy chỉnh nếu cần
                .setSubject(user.getEmail()) // Đặt email là chủ đề của token
                .setIssuedAt(new Date(System.currentTimeMillis())) // Thời gian kích hoạt
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Thay đổi thành 24 giờ
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256) // Sử dụng khóa bí mật để
                                                                                               // ký
                .compact(); // Trả về token đã tạo
    }

    // Tạo refresh token
    public String generateRefreshToken(NguoiDung user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 ngày
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // Trích xuất email từ token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) // Sử dụng khóa bí mật để xác thực
                .build()
                .parseClaimsJws(token) // Phân tích token
                .getBody()
                .getSubject(); // Lấy email từ token
    }

    // Xác thực token với thông tin người dùng (UserDetails)
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public List<String> extractRoles(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build().parseClaimsJws(token).getBody().get("roles", List.class);
    }

    // Kiểm tra xem token đã hết hạn chưa
    private Boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) // Sử dụng khóa bí mật để xác thực
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date()); // Kiểm tra ngày hết hạn
    }
}
