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
@Table(name = "HoaDon")
public class HoaDon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_NguoiDung")
    private NguoiDung nguoiDung;

    private float tongTien;

    @Temporal(TemporalType.DATE)
    private Date ngayThanhToan;

    private boolean phuongThucThanhToan;
    private boolean trangThai;
    
    @OneToOne(mappedBy = "hoaDon")
    @JsonBackReference
    private ChiTietHoaDon chiTietHoaDon; // Mối quan hệ 1-1 với đặt tour
    
}
