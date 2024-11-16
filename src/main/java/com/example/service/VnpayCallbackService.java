package com.example.service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class VnpayCallbackService {

    private static final String CALLBACK_URL = "http://localhost:8080/api/vnpay/callback"; // Đảm bảo URL này là chính xác

    public void callVnpayCallback(Map<String, String> vnpayParams) {
        // Tạo RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Đặt headers cho yêu cầu POST
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Tạo entity cho yêu cầu POST
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(vnpayParams, headers);

        // Gửi yêu cầu POST tới VNPay callback API
        ResponseEntity<String> response = restTemplate.exchange(CALLBACK_URL, HttpMethod.POST, entity, String.class);

        // Xử lý phản hồi
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Callback thành công: " + response.getBody());
        } else {
            System.out.println("Lỗi khi gọi callback: " + response.getStatusCode());
        }
    }
}