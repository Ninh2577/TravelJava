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
@Table(name = "GioHangDanhSachNguoiDiCung")
public class GioHangDanhSachNguoiDiCung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_ChiTietGioHang")
    private ChiTietGioHang chiTietGioHang;
 
    private String hoTen;
    private String email;
    private String soDienThoai;
    private Date namSinh;
   
}
