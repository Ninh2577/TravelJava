package com.example.Config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Entity.NguoiDung;
import com.example.Entity.VaiTro;
import com.example.Repository.NguoiDungRepository;
import com.example.Utils.CustomAccessDeniedHandler;
import com.example.Utils.JwtAuthFilter;
import com.example.service.NguoiDungService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private JwtAuthFilter authFilter; // Inject JwtAuthFilter để xử lý JWT
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new NguoiDungService(); // Đảm bảo lớp này sử dụng email làm tên đăng nhập
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // return http
    // .csrf(AbstractHttpConfigurer::disable)
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers("/admin/**").hasAnyAuthority("admin", "nhanvien")
    // .anyRequest().permitAll())
    // // .oauth2Login(oauth2 -> oauth2
    // // // .successHandler((request, response, authentication) -> {
    // // // // Lấy thông tin người dùng từ OAuth2User
    // // // OAuth2User googleUser = (OAuth2User) authentication.getPrincipal();
    // // // String role = (String) googleUser.getAttributes().get("role");
    // // // System.out.println("roles: " + role);

    // // // // Kiểm tra vai trò và điều hướng dựa trên vai trò
    // // // if ("admin".equals(role) || "nhanvien".equals(role)) {
    // // // response.sendRedirect("http://localhost:3000/admin");
    // // // } else {
    // // // response.sendRedirect("http://localhost:3000/");
    // // // }
    // // // }))
    // // .successHandler((request, response, authentication) -> {
    // // OAuth2User googleUser = (OAuth2User) authentication.getPrincipal();
    // // String email = (String) googleUser.getAttributes().get("email");
    // // Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findByEmail(email);

    // // if (nguoiDungOpt.isPresent()) {
    // // NguoiDung nguoiDung = nguoiDungOpt.get();
    // // String role = nguoiDung.getVaiTro().getVaiTro();
    // // System.out.println("roles: " + role);

    // // // Điều hướng dựa trên vai trò người dùng
    // // if ("admin".equals(role) || "nhanvien".equals(role)) {
    // // response.sendRedirect("http://localhost:3000/admin");
    // // } else {
    // // response.sendRedirect("http://localhost:3000/");
    // // }
    // // } else {
    // // // Nếu người dùng chưa có tài khoản, tạo mới
    // // VaiTro vaiTro = new VaiTro();
    // // vaiTro.setId(3); // Vai trò mặc định (người dùng thường hoặc khách)

    // // NguoiDung nguoiDung = new NguoiDung();
    // // nguoiDung.setHoTen((String) googleUser.getAttributes().get("name"));
    // // nguoiDung.setEmail(email);
    // // nguoiDung.setHinhAnh((String) googleUser.getAttributes().get("picture"));
    // // nguoiDung.setVaiTro(vaiTro);

    // // nguoiDungRepository.save(nguoiDung);

    // // // Sau khi tạo tài khoản, điều hướng người dùng đến trang chính hoặc trang
    // // admin
    // // response.sendRedirect("http://localhost:3000/"); // Hoặc /admin nếu bạn
    // muốn
    // // chuyển
    // // // hướng đến admin
    // // }
    // // }))
    // .build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**")
                        .hasAnyAuthority("admin", "nhanvien")
                        // Chỉ cho phép các vai trò này
                        // .requestMatchers("/admin/**").hasRole("Admin")
                        // .requestMatchers("/admin/**").hasAuthority("Admin") // Sử dụng hasAuthority
                        .anyRequest().permitAll() // Tất cả các yêu cầu khác đều được phép truy cập
                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler)) // Sử dụng AccessDeniedHandler tùy
                                                                                      // chỉnh
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}