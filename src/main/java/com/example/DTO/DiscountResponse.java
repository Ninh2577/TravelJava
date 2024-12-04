package com.example.DTO;

public class DiscountResponse {
    private float tongTienSauGiam;

    public DiscountResponse(float tongTienSauGiam) {
        this.tongTienSauGiam = tongTienSauGiam;
    }

    public float getTongTienSauGiam() {
        return tongTienSauGiam;
    }

    public void setTongTienSauGiam(float tongTienSauGiam) {
        this.tongTienSauGiam = tongTienSauGiam;
    }
}
