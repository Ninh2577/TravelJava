//package com.example.Controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
////Controller xử lý thanh toán
//@RestController
//@RequestMapping("/api/payment")
//@CrossOrigin( origins = "http://localhost:3000")
//public class PaymentController {
//
//	@Value("${vn.pay.url}")
//	private String vnPayUrl;
//
//	@Value("${vn.pay.partnerCode}")
//	private String partnerCode;
//
//	@Value("${vn.pay.secretKey}")
//	private String secretKey;
//
//	@PostMapping("/vnpay")
//	public String createPayment(@RequestBody PaymentRequest paymentRequest) {
//		String vnp_TxnRef = generateTransactionReference(); // Tạo mã giao dịch
//		String vnp_Amount = paymentRequest.getAmount(); // Số tiền thanh toán
//		String vnp_OrderInfo = paymentRequest.getOrderInfo(); // Thông tin đơn hàng
//		String vnp_Locale = "vn"; // Ngôn ngữ
//		String vnp_ReturnUrl = "http://yourdomain.com/vnpay-return"; // URL trả về khi thanh toán thành công
//
//		// Tạo chữ ký bảo mật
//		String vnp_SecureHash = generateVnPayHash(vnp_TxnRef, vnp_Amount, vnp_OrderInfo);
//
//		// Tạo URL thanh toán VNPay
//		String paymentUrl = vnPayUrl + "?vnp_TxnRef=" + vnp_TxnRef + "&vnp_Amount=" + vnp_Amount + "&vnp_OrderInfo="
//				+ vnp_OrderInfo + "&vnp_Locale=" + vnp_Locale + "&vnp_ReturnUrl=" + vnp_ReturnUrl + "&vnp_SecureHash="
//				+ vnp_SecureHash;
//
//		return paymentUrl; // Trả về URL thanh toán VNPay
//	}
//
//	private String generateTransactionReference() {
//		return "123456"; // Logic để tạo mã giao dịch duy nhất
//	}
//
//	private String generateVnPayHash(String vnp_TxnRef, String vnp_Amount, String vnp_OrderInfo) {
//		// Logic để tạo chữ ký bảo mật (hash)
//		return "securehash";
//	}
//}
