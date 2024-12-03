package com.example.DTO;

import java.util.List;

public class PhanQuyenRequest {
    private String emailNhanVien;
    private List<Integer> idChucNang;

    // Getter và Setter cho emailNhanVien
    public String getEmailNhanVien() {
        return emailNhanVien;
    }

    public void setEmailNhanVien(String emailNhanVien) {
        this.emailNhanVien = emailNhanVien;
    }

    // Getter và Setter cho idChucNang
    public List<Integer> getIdChucNang() {
        return idChucNang;
    }

    public void setIdChucNang(List<Integer> idChucNang) {
        this.idChucNang = idChucNang;
    }
}

