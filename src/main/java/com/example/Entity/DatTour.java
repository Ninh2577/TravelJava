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
@Table(name = "DatTour")
public class DatTour implements Serializable {
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
    @JoinColumn(name = "id_HoaDon", unique = true)  // Đảm bảo tính duy nhất
    @JsonBackReference
    private HoaDon hoaDon;  // Mối quan hệ 1-1 với HoaDon
    @OneToOne
    @JoinColumn(name = "id_ChiTietGioHang", unique = true)  // New field for one-to-one relationship
    @JsonBackReference
    private ChiTietGioHang chiTietGioHang;
}
