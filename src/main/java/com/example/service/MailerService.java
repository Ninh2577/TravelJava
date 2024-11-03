package com.example.service;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.Entity.MailModel;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailerService {
	@Autowired
	JavaMailSender sender;

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
		message.setSubject("[TVG] - Xác nhận thông tin tài khoản");
	    message.setText("Mã xác thực tài khoản của bạn là: " + otp + "\nMã xác thực có hiệu lực trong vòng 2 phút.");
		sender.send(message);
	}
}
