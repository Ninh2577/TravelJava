package com.example.DTO;

public class DiscountRequest {
    private Integer bienTheTourId;
    private String maGiamGia;
    private float tongTien;

    public Integer getBienTheTourId() {
        return bienTheTourId;
    }

    public void setBienTheTourId(Integer bienTheTourId) {
        this.bienTheTourId = bienTheTourId;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}

