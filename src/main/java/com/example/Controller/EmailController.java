package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.service.MailerService;

import java.util.Date;

@RestController
@RequestMapping("/test/email/api")
public class EmailController {

    @Autowired
    private MailerService mailerService;

    @PostMapping("/huy")
    public ResponseEntity<String> huyTour(@RequestBody CancelTourRequest cancelTourRequest) {
        try {
            mailerService.sendCancelTourEmail(
                cancelTourRequest.getToEmail(),
                cancelTourRequest.getUserName(),
                cancelTourRequest.getTotalAmount(),
                cancelTourRequest.getPaymentDate(),
                cancelTourRequest.getCancelReason()
            );
            return ResponseEntity.ok("Email hủy tour đã được gửi thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Gửi email thất bại: " + e.getMessage());
        }
    }

    public static class CancelTourRequest {
        private String toEmail;
        private String userName;
        private float totalAmount;
        private Date paymentDate;
        private String cancelReason;

        // Getters và Setters
        public String getToEmail() { return toEmail; }
        public void setToEmail(String toEmail) { this.toEmail = toEmail; }

        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }

        public float getTotalAmount() { return totalAmount; }
        public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }

        public Date getPaymentDate() { return paymentDate; }
        public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

        public String getCancelReason() { return cancelReason; }
        public void setCancelReason(String cancelReason) { this.cancelReason = cancelReason; }
    }
}
