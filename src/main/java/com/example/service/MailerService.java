package com.example.service;

import java.util.List;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.DTO.ChiTietHoaDonsDTO;
import com.example.Entity.MailModel;
import com.example.Repository.HoaDonRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class MailerService {

	@Autowired
	JavaMailSender sender;

	@Autowired
	HoaDonRepository hoaDonRepository;

	List<MailModel> list = new ArrayList<MailModel>();

	public void push(String to, String subject, String content) {
		this.push(new MailModel(to, subject, content));
	}

	public void push(MailModel mailModel) {
		list.add(mailModel);
	}

	@Scheduled(fixedRate = 10000)
	public void run() {
		System.out.println("send mail");
		while (!list.isEmpty()) {
			MailModel mailModel = list.remove(0);
			MimeMessage minMessage = sender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(minMessage, true, "utf-8");
				helper.setFrom(mailModel.getFrom());
				helper.setTo(mailModel.getTo());
				helper.setSubject(mailModel.getSubject());
				helper.setText(mailModel.getContent());
				for (String cc : mailModel.getCc()) {
					helper.setCc(cc);
				}
				for (String bcc : mailModel.getBcc()) {
					helper.setBcc(bcc);
				}
				for (File file : mailModel.getFiles()) {
					helper.addAttachment(file.getName(), file);
				}
				sender.send(minMessage);

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendOtpEmail(String toEmail, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("Xác nhận thông tin tài khoản");
		message.setText("Mã xác thực tài khoản của bạn là: " + otp + "\nMã xác thực có hiệu lực trong vòng 2 phút.");
		sender.send(message);
	}

	// public void sendCancelTourEmail(String toEmail, String userName, float
	// totalAmount, Date paymentDate, String cancelReason, String tenTour) {
	// String subject = "Thông Báo Hủy Tour";
	// String body = "Kính gửi " + userName + ",\n\n"
	// + "Tour: " + tenTour + "\n"
	// + "Chúng tôi xin thông báo rằng tour của bạn đã bị hủy với lý do: " +
	// cancelReason + ".\n"
	// + "Tổng tiền đã thanh toán là: " + totalAmount + " VNĐ.\n"
	// + "Ngày thanh toán: " + paymentDate + ".\n\n"
	// + "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.";

	// // Cấu hình email
	// SimpleMailMessage message = new SimpleMailMessage();
	// message.setFrom("noreply@tourservice.com");
	// message.setTo(toEmail);
	// message.setSubject(subject);
	// message.setText(body);

	// // Gửi email
	// sender.send(message);
	// System.out.println("Gửi email đến: " + toEmail); // Kiểm tra xem địa chỉ
	// email có đúng không

	// }
	@Async
	public void sendInvoiceEmail(Integer idHoaDon) throws MessagingException {
		// Truy vấn thông tin chi tiết hóa đơn và người đi cùng
		List<ChiTietHoaDonsDTO> chiTietHoaDons = hoaDonRepository.getHoaDonChiTietDanhSachNguoiDiCung(idHoaDon);
		String toEmail = chiTietHoaDons.get(0).getNguoiDungEmail(); // Email người đại diện
		String hoTen = chiTietHoaDons.get(0).getNguoiDungHoTen(); // Tên người đại diện
		String tenTour = chiTietHoaDons.get(0).getTenTour(); // Tên tour
		String maTour = chiTietHoaDons.get(0).getMoTa(); // Mã tour
		boolean trangThai = chiTietHoaDons.get(0).isHoaDonTrangThai(); // Trạng thái hóa đơn
		Date paymentDate = chiTietHoaDons.get(0).getNgayThanhToan(); // Ngày thanh toán
		float totalAmount = chiTietHoaDons.get(0).getThanhTien(); // Tổng tiền thanh toán
		String trangThais = trangThai ? "Đã thanh toán" : "Đã Hủy";

		// Định dạng ngày thanh toán
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(paymentDate);

		// Định dạng tiền tệ
		NumberFormat currencyFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
		String formattedAmount = currencyFormat.format(totalAmount) + " đ";

		// Tạo nội dung email HTML
		StringBuilder body = new StringBuilder();
		body.append("<html><body style='font-family: Arial, sans-serif; color: #333;'>");
		body.append("<h2 style='color: #2e6fa9;'>Kính gửi ").append(hoTen).append(",</h2>");
		body.append("<p style='font-size: 16px;'>Thông tin tour đã thanh toán:</p>");
		body.append("<p><strong style='font-size: 16px;'>Tên tour:</strong> ").append(tenTour).append("</p>");
		body.append("<p><strong style='font-size: 16px;'>Mã tour:</strong> ").append(maTour).append("</p>");
		body.append("<p><strong style='font-size: 16px;'>Trạng thái thanh toán:</strong> ").append(trangThais)
				.append("</p>");
		body.append("<p><strong style='font-size: 16px;'>Ngày thanh toán:</strong> ").append(formattedDate)
				.append("</p>");
		body.append("<p><strong style='font-size: 16px;'>Tổng tiền thanh toán:</strong> ").append(formattedAmount)
				.append("</p>");
		body.append("<br><br>");
		body.append("<h3 style='color: #2e6fa9;'>Danh sách người đi cùng:</h3>");
		body.append(
				"<table border='1' cellpadding='5' cellspacing='0' style='border-collapse: collapse; width: 100%;'>");
		body.append(
				"<tr style='background-color: #f2f2f2;'><th style='padding: 10px; text-align: left;'>STT</th><th style='padding: 10px; text-align: left;'>Họ Tên</th><th style='padding: 10px; text-align: left;'>Email</th><th style='padding: 10px; text-align: left;'>Số điện thoại</th><th style='padding: 10px; text-align: left;'>Năm Sinh</th><th style='padding: 10px; text-align: left;'>Giá người lớn</th><th style='padding: 10px; text-align: left;'>Giá trẻ em</th><th style='padding: 10px; text-align: left;'>Giá Tiền</th></tr>");

		// Duyệt qua danh sách người đi cùng
		int index = 1;
		for (ChiTietHoaDonsDTO chiTietHoaDon : chiTietHoaDons) {
			String userName = chiTietHoaDon.getHoTen();
			String email = chiTietHoaDon.getEmail();
			String sdts = chiTietHoaDon.getSoDienThoai();
			Date namsinh = chiTietHoaDon.getNamSinh();
			Float giaNgLon = chiTietHoaDon.getGiaNguoiLon();
			Float giaTreEm = chiTietHoaDon.getGiaTreEm();
			Float giaTien = chiTietHoaDon.getThanhTien();

			String formattedNamSinh = new SimpleDateFormat("dd-MM-yyyy").format(namsinh);

			// Định dạng tiền tệ cho người lớn, trẻ em và tổng tiền
			String formattedGiaNgLon = currencyFormat.format(giaNgLon) + " đ";
			String formattedGiaTreEm = currencyFormat.format(giaTreEm) + " đ";
			String formattedGiaTien = currencyFormat.format(giaTien) + " đ";

			body.append("<tr>");
			body.append("<td style='padding: 8px; text-align: left;'>").append(index++).append("</td>");
			body.append("<td style='padding: 8px; text-align: left;'>").append(userName).append("</td>");
			body.append("<td style='padding: 8px; text-align: left;'>").append(email).append("</td>");
			body.append("<td style='padding: 8px; text-align: left;'>").append(sdts).append("</td>");
			body.append("<td style='padding: 8px; text-align: left;'>").append(formattedNamSinh).append("</td>");
			body.append("<td style='padding: 8px; text-align: left;'>").append(formattedGiaNgLon).append("</td>");
			body.append("<td style='padding: 8px; text-align: left;'>").append(formattedGiaTreEm).append("</td>");
			body.append("<td style='padding: 8px; text-align: left;'>").append(formattedGiaTien).append("</td>");
			body.append("</tr>");
		}

		body.append("</table>");
		body.append("<br><br>");
		body.append("<p style='font-size: 16px;'>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.</p>");
		body.append("</body></html>");

		// Gửi email HTML
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom("noreply@tourservice.com");
		helper.setTo(toEmail);
		helper.setSubject("Thông Báo Hóa Đơn #" + idHoaDon);
		helper.setText(body.toString(), true); // true để thiết lập HTML

		sender.send(message);
		System.out.println("Gửi email thành công tới: " + toEmail);
	}

	// -----------temp

	public void sendCancelTourEmail(String toEmail, String userName, float totalAmount, Date paymentDate,
			String cancelReason, String tenTour) {
		String subject = "Thông Báo Hủy Tour";

		// Định dạng ngày theo kiểu dd-MM-yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(paymentDate);

		// Định dạng tiền theo kiểu VNĐ
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String formattedAmount = currencyFormat.format(totalAmount);

		// Loại bỏ ký tự ₫ thừa từ tiền tệ
		formattedAmount = formattedAmount.replace("₫", "").trim() + " ₫";

		String body = "Kính gửi " + userName + ",\n\n"
				+ "Tour: " + tenTour + "\n"
				+ "Chúng tôi xin thông báo rằng tour của bạn đã bị hủy với lý do: " + cancelReason + ".\n"
				+ "Tổng tiền đã thanh toán là: " + formattedAmount + ".\n"
				+ "Ngày thanh toán: " + formattedDate + ".\n\n"
				+ "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.";

		// Cấu hình email
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@tourservice.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);

		// Gửi email
		sender.send(message);
		System.out.println("Gửi email đến: " + toEmail); // Kiểm tra xem địa chỉ email có đúng không
	}
}
