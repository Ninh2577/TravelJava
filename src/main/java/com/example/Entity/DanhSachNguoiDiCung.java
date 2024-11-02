package com.example.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DanhSachNguoiDiCung")
public class DanhSachNguoiDiCung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_NguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "id_Tour")
    private Tour tour;

    private String hoTen;
    private String email;
    private String soDienThoai;

    @Temporal(TemporalType.DATE)
    private Date namSinh;
    
    @JsonIgnore
    @OneToMany(mappedBy = "danhSachNguoiDiDung")
    private List<ChiTietGioHang> chiTietGioHangs;
}
