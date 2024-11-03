package com.example.Utils;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.service.NguoiDungService;

import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            System.out.println("Token received: " + token);

            try {
                email = jwtUtil.extractEmail(token);
            } catch (SignatureException e) {
                // Log lỗi và trả về lỗi không xác thực
                System.err.println("Invalid JWT signature: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            } catch (Exception e) {
                // Log lỗi khác và trả về lỗi không xác thực
                System.err.println("JWT parsing error: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = nguoiDungService.loadUserByUsername(email);
            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Kiểm tra quyền truy cập (Role Check)
        if (token != null) {
            List<String> roles = jwtUtil.extractRoles(token);
            System.out.println("Roles extracted from token: " + roles); // Log các vai trò để kiểm tra

            // Thêm các câu lệnh ghi log
            System.out.println("User roles: " + roles);
            System.out.println("Request URI: " + request.getRequestURI());
            // Nếu yêu cầu đến các URL admin và không có quyền Admin
            if (request.getRequestURI().startsWith("/admin/") && !roles.contains("Admin")
                    && !roles.contains("Nhân viên đăng bài")
                    && !roles.contains("Nhân viên quản lý tour")
                    && !roles.contains("Nhân viên")) {
                System.out.println("Access Denied for user with roles: " + roles);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return; // Ngăn không cho tiếp tục
            }
        }

        // Tiếp tục chuỗi bộ lọc
        filterChain.doFilter(request, response);
    }
}
