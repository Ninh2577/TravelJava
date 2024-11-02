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
@Table(name = "NguoiDung")
public class NguoiDung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_VaiTro")
    private VaiTro vaiTro;

    private String hoTen;
    private String email;
    private String soDienThoai;
    private String diaChi;
    private String matKhau;
    private String hinhAnh;
    private boolean gioiTinh;
    private Integer tuoi;

    @Temporal(TemporalType.DATE)
    private Date namSinh;
    

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<HoaDon> hoaDons;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<BaiViet> baiViets;
    
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<ChiTietGioHang> chiTietGioHangs;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<DanhSachNguoiDiCung> danhSachNguoiDiCungs;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<GiamGia> giamGias;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<YeuThich> yeuThichs;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<DanhGia> danhGias;
}


