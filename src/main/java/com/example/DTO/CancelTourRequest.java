package com.example.DTO;

public class CancelTourRequest {

    private Integer chiTietHoaDonId;  // ID chi tiết hóa đơn
    private String ghiChu;  // Lý do hủy

    // Getters và Setters
    public Integer getChiTietHoaDonId() {
        return chiTietHoaDonId;
    }

    public void setChiTietHoaDonId(Integer chiTietHoaDonId) {
        this.chiTietHoaDonId = chiTietHoaDonId;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
