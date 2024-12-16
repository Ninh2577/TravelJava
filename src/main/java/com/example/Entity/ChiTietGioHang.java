package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ChiTietGioHang")
public class ChiTietGioHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_NguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "id_BienTheTour")
    private BienTheTour bienTheTour;

    private float tongTien;
    // private float tongTienSauGiam; // Tổng tiền sau khi giảm giá
    // private String maGiamGia; // Mã giảm giá được nhập

    private String moTa;
    private Integer soNguoi;
}
