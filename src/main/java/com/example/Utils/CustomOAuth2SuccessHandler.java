package com.example.Utils;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.Entity.NguoiDung;
import com.example.Entity.VaiTro;
import com.example.Repository.NguoiDungRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final NguoiDungRepository nguoiDungRepository;

    public CustomOAuth2SuccessHandler(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        OAuth2User googleUser = (OAuth2User) authentication.getPrincipal();
        String email = (String) googleUser.getAttributes().get("email");
        Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findByEmail(email);

        if (nguoiDungOpt.isPresent()) {
            NguoiDung nguoiDung = nguoiDungOpt.get();
            String role = nguoiDung.getVaiTro().getVaiTro();
            if ("admin".equals(role) || "nhanvien".equals(role)) {
                response.sendRedirect("http://localhost:3000/admin");
            } else {
                response.sendRedirect("http://localhost:3000/");
            }
        } else {
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(3); // Vai trò mặc định (User)

            NguoiDung newNguoiDung = new NguoiDung();
            newNguoiDung.setHoTen((String) googleUser.getAttributes().get("name"));
            newNguoiDung.setEmail(email);
            newNguoiDung.setHinhAnh((String) googleUser.getAttributes().get("picture"));
            newNguoiDung.setVaiTro(vaiTro);

            nguoiDungRepository.save(newNguoiDung);
            response.sendRedirect("http://localhost:3000/");
        }
    }
}
