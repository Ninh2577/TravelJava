package com.example.Entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float giaNguoiLon;
    private float giaTreEm;
    private float thanhTien;

    @Temporal(TemporalType.DATE)
    private Date ngayDat;
    private String moTa;
    private boolean trangThai;

    @OneToOne
    @JoinColumn(name = "id_HoaDon", unique = true, nullable = false)
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "id_BienTheTour", nullable = false)
    private BienTheTour bienTheTour;
}
