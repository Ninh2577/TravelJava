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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasAnyAuthority("admin", "nhanvien")
                        .anyRequest().permitAll())
                .oauth2Login(oauth2 -> oauth2
                        .successHandler((request, response, authentication) -> {
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
                        }))
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
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