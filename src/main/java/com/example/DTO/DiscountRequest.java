package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRequest {
    private String maGiamGia;
    private Integer bienTheTourId;
    private Float totalTien;
}
