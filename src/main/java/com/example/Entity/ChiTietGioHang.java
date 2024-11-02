package com.example.Entity;

import java.io.Serializable;

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
//    @JsonBackReference
    private BienTheTour bienTheTour;

    @ManyToOne
    @JoinColumn(name = "id_DanhSachNguoiDiDung")
    private DanhSachNguoiDiCung danhSachNguoiDiDung;

    private float tongTien;
    private String moTa;
    private Integer soNguoi;
    @OneToOne(mappedBy = "chiTietGioHang")
    @JsonBackReference
    private DatTour datTour;
}
