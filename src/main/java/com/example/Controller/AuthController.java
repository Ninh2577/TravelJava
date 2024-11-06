package com.example.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.NguoiDung;
import com.example.Entity.VaiTro;
import com.example.Repository.NguoiDungRepository;
import com.example.Utils.EncryptionUtil;
import com.example.Utils.JwtUtil;
import com.example.service.AuthService;
import com.example.service.MailerService;
import com.example.service.NguoiDungService;
import com.example.service.OTPService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    OTPService otpService;

    @Autowired
    MailerService mailerService;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @GetMapping("/auth/google/callback")
    public String googleCallback(OAuth2AuthenticationToken authentication) {
        // Xử lý thông tin người dùng từ Google
        String email = authentication.getPrincipal().getAttribute("email");
        // Bạn có thể kiểm tra xem email này đã tồn tại trong database chưa, 
        // nếu chưa thì có thể tạo người dùng mới

        return "Đăng nhập thành công với Google! Email: " + email;
    }

    // API đăng ký
    @PostMapping("/dangKy")
    public ResponseEntity<Map<String, String>> register(@RequestBody NguoiDung nguoiDung) {

        // Kiểm tra nếu email đã tồn tại
        if (nguoiDungService.existsByEmail(nguoiDung.getEmail())) {
            Map<String, String> response = new HashMap<>();
            response.put("msg", "Email đã tồn tại.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        System.out.println(nguoiDung);
        VaiTro vaiTro = new VaiTro();
        vaiTro.setId(3);
        nguoiDung.setVaiTro(vaiTro); // Gán vai trò cho người dùng
        // Lưu người dùng vào cơ sở dữ liệu
        nguoiDungService.addNguoiDungke(nguoiDung);

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Đăng ký thành công!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // @PostMapping("/login")
    // public ResponseEntity<Map<String, Object>> login(@RequestBody NguoiDung
    // loginRequest,
    // HttpServletResponse response) {
    // try {
    // // Xác thực người dùng
    // NguoiDung nguoiDung = authService.login(loginRequest.getEmail(),
    // loginRequest.getMatKhau());

    // // Tạo JWT token
    // String token = jwtUtil.generateToken(nguoiDung);

    // // Lưu tokezzzn vào cookie
    // Cookie cookie = new Cookie("token", token);
    // cookie.setHttpOnly(true); // Bảo mật token không bị truy cập bởi JavaScript
    // cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
    // cookie.setMaxAge(60 * 60 * 10); // 10 giờ
    // cookie.setSecure(true); // Đảm bảo cookie chỉ gửi qua HTTPS
    // response.addCookie(cookie);

    // // Tạo đối tượng response với token và thông báo thành công
    // Map<String, Object> responseBody = new HashMap<>();
    // responseBody.put("message", "Đăng nhập thành công!");
    // responseBody.put("token", token); // Trả về token trong phản hồi
    // responseBody.put("role", nguoiDung.getVaiTro().getVaiTro()); // Thêm vai trò
    // vào phản hồi
    // responseBody.put("hoTen", nguoiDung.getHoTen());
    // responseBody.put("email", nguoiDung.getEmail());
    // responseBody.put("diaChi", nguoiDung.getDiaChi());

    // // Trả về phản hồi thành công với mã 200 (OK)
    // return ResponseEntity.ok(responseBody);
    // } catch (Exception e) {
    // // Trả về phản hồi lỗi với mã 400 (Bad Request)
    // Map<String, Object> errorResponse = new HashMap<>();
    // errorResponse.put("message", "Đăng nhập thất bại: " + e.getMessage());
    // return ResponseEntity.badRequest().body(errorResponse);
    // }
    // }
    @PostMapping("/dangNhap")
    public ResponseEntity<Map<String, Object>> login(@RequestBody NguoiDung loginRequest,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            // Xác thực người dùng
            NguoiDung nguoiDung = authService.login(loginRequest.getEmail(), loginRequest.getMatKhau());

            // Tạo JWT token
            String token = jwtUtil.generateToken(nguoiDung);

            // Lưu thông tin người dùng vào session
//            HttpSession session = request.getSession();
//            session.setAttribute("nguoiDung", nguoiDung);
//            session.setAttribute("token", token);

            // Lưu tokezzzn vào cookie
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true); // Bảo mật token không bị truy cập bởi JavaScript
            cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
            cookie.setMaxAge(60 * 60 * 10); // 10 giờ
            cookie.setSecure(true); // Đảm bảo cookie chỉ gửi qua HTTPS
            response.addCookie(cookie);

            // Tạo đối tượng response với token và thông báo thành công
         // Tạo đối tượng response với token và thông báo thành công
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Đăng nhập thành công!");
            responseBody.put("token", token); // Trả về token trong phản hồi
            responseBody.put("role", nguoiDung.getVaiTro().getVaiTro());
            responseBody.put("hoTen", nguoiDung.getHoTen());
            responseBody.put("email", nguoiDung.getEmail());
            responseBody.put("diaChi", nguoiDung.getDiaChi());
            responseBody.put("id", nguoiDung.getId());

            // Trả về phản hồi thành công với mã 200 (OK)
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            // Trả về phản hồi lỗi với mã 400 (Bad Request)
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Đăng nhập thất bại: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // Tạo một cookie mới với thời gian sống = 0 để xóa cookie
        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true); // Đảm bảo cookie vẫn không thể truy cập bởi JavaScript
        cookie.setPath("/"); // Đặt đường dẫn giống như khi tạo cookie
        cookie.setMaxAge(0); // Đặt thời gian sống của cookie là 0 để xóa nó
        cookie.setSecure(true); // Nếu đang sử dụng HTTPS
        response.addCookie(cookie); // Thêm cookie vào phản hồi để xóa nó

        // Trả về phản hồi thành công
        return ResponseEntity.ok().body("Đăng xuất thành công!");
    }

    @GetMapping("/user-info")
    public ResponseEntity<Map<String, Object>> getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("nguoiDung") != null) {
            NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", nguoiDung.getId());
            userInfo.put("hoTen", nguoiDung.getHoTen());
            userInfo.put("email", nguoiDung.getEmail());
            userInfo.put("diaChi", nguoiDung.getDiaChi());
            userInfo.put("role", nguoiDung.getVaiTro());
            userInfo.put("token", session.getAttribute("token"));

            System.out.println("hoTen" + nguoiDung.getHoTen());
            System.out.println("email" + nguoiDung.getEmail());
            System.out.println("diaChi" + nguoiDung.getDiaChi());
            System.out.println("role" + nguoiDung.getVaiTro().getVaiTro());
            System.out.println("token" + session.getAttribute("token"));
            System.out.println("id: " + nguoiDung.getId());
            return ResponseEntity.ok(userInfo);
        } else {
            // Trả về phản hồi lỗi với mã 400 (Bad Request)
            Map<String, Object> errorResponse = new HashMap<>();
            // errorResponse.put("message", "Đăng nhập thất bại:");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/send-otp")
    public ResponseEntity<Map<String, Object>> sendOtp(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");
        Map<String, Object> response = new HashMap<>();

        if (!nguoiDungRepository.existsByEmail(email)) {
            response.put("message", "Email không tồn tại!");
            return ResponseEntity.badRequest().body(response);
        }

        if (!otpService.canResendOtp(email)) {
            response.put("message", "Vui lòng đợi trước khi yêu cầu mã OTP mới!");
            return ResponseEntity.badRequest().body(response);
        } else {
            // Tạo mã OTP và gửi email
            String generatedOtp = otpService.generateOTP(email);
            mailerService.sendOtpEmail(email, generatedOtp);

            session.setAttribute("otp", generatedOtp);
            session.setAttribute("otpExpiration", System.currentTimeMillis() +
                    TimeUnit.MINUTES.toMillis(2));

            response.put("message", "Mã OTP đã được gửi đến: " + email);
            response.put("otp", generatedOtp); // Trả về mã OTP
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request,
            HttpSession session) {
        String otp = request.get("otp"); // Chỉ lấy OTP từ request
        String email = request.get("email"); // Lấy email từ request
        // Ghi lại OTP nhận được và email để kiểm tra
        System.out.println("Received OTP: " + otp + " for email: " + email);

        boolean isValid = otpService.isValidOtp(email, otp); // Kiểm tra OTP
        Map<String, Object> responseBody = new HashMap<>();

        if (isValid) {
            // Xóa OTP và email khỏi session sau khi xác minh thành công
            session.removeAttribute("otp");
            session.removeAttribute("email");
            responseBody.put("message", "OTP đã xác minh thành công.");
            return ResponseEntity.ok(responseBody);
        } else {
            responseBody.put("message", "OTP không đúng hoặc đã hết hạn!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        if (!otpService.canResendOtp(email)) {
            long waitTime = otpService.getWaitTime(email); // Phương thức mới để lấy thời gian chờ
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Bạn cần đợi " + waitTime + " giây trước khi gửi lại OTP.");
        }
        if (!otpService.canResendOtp(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bạn cần đợi trước khi gửi lại OTP.");
        }

        String newOtp = otpService.generateNewOtp(email);
        // Gửi mã OTP mới đến email (có thể sử dụng email service)

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Mã OTP mới đã được gửi đến email của bạn." + email);
        responseBody.put("otp", newOtp); // Chỉ để tham khảo, không nên gửi mã OTP cho client
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email"); // Lấy email từ request
        String newPassword = request.get("newPassword");
        String confirmPassword = request.get("confirmPassword"); // Thêm xác nhận mật khẩu
        Map<String, Object> responseBody = new HashMap<>();

        // Kiểm tra xem mật khẩu mới và xác nhận có trùng nhau không
        if (!newPassword.equals(confirmPassword)) {
            responseBody.put("message", "Mật khẩu mới và xác nhận mật khẩu không khớp");
            return ResponseEntity.badRequest().body(responseBody);
        }

        try {
            // Gọi phương thức trong AuthService để cập nhật mật khẩu
            authService.updatePassword(email, newPassword);
            responseBody.put("message", "Đổi mật khẩu thành công");
            System.out.println("Mật khẩu mới đã được đặt cho email: " + email);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            // Trả về phản hồi lỗi với mã 400 (Bad Request)
            responseBody.put("message", "Đổi mật khẩu thất bại: " + e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
}
