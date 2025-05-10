package com.example.service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.OTP;

import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
	private static final int OTP_EXPIRATION_TIME = 2; // 2 phút
	private static final int OTP_RESEND_DELAY = 60; // 60 giây
	@Autowired
	private MailerService mailerService;
	private ConcurrentHashMap<String, OTP> otpCache = new ConcurrentHashMap<>();

	public String generateOTP(String email) {
		String otp = String.format("%06d", new Random().nextInt(999999));
		OTP otpDetails = new OTP(otp, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(OTP_EXPIRATION_TIME), 0);
		otpCache.put(email, otpDetails);
		return otp;
	}

	public boolean isValidOtp(String email, String otp) {
		OTP otpDetails = otpCache.get(email);
		if (otpDetails != null && otpDetails.getOtp().equals(otp)
				&& System.currentTimeMillis() <= otpDetails.getExpirationTime()) {
			otpCache.remove(email); // Xóa OTP sau khi xác thực thành công
			return true;
		}
		return false;
	}

	public boolean canResendOtp(String email) {
		OTP otpDetails = otpCache.get(email);
		return otpDetails == null || System.currentTimeMillis() > otpDetails.getCreationTime()
				+ TimeUnit.SECONDS.toMillis(OTP_RESEND_DELAY);
	}

	public String generateNewOtp(String email) {
		if (canResendOtp(email)) {
			String newOtp = generateOTP(email); // Tạo mã OTP mới và gửi email
			sendOtpEmail(email, newOtp);
			return newOtp;
		} else {
			return null;
		}
	}

	public long getWaitTime(String email) {
		OTP otpDetails = otpCache.get(email);
		if (otpDetails != null) {
			long remainingTime = (otpDetails.getCreationTime() + TimeUnit.SECONDS.toMillis(OTP_RESEND_DELAY))
					- System.currentTimeMillis();
			return TimeUnit.MILLISECONDS.toSeconds(remainingTime);
		}
		return 0; // Có thể gửi lại nếu không có OTP trước đó
	}

	private void sendOtpEmail(String toEmail, String otp) {
		mailerService.sendOtpEmail(toEmail, otp); // Gọi phương thức gửi email trong MailerService
	}
}
