package com.example.Entity;

import java.io.Serializable;
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
@Table(name = "Tour")
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_DanhMucTour")
    private DanhMucTour danhMucTour;

    @ManyToOne
    @JoinColumn(name = "id_LoaiTour")
    private LoaiTour loaiTour;

    private String tenTour;
    private String soNgay;
    private String moTa;
    private String hinhAnh;
    private Integer soLuongNguoi;
    private Integer soTour;
    private boolean trangThai;
    private String noiDung;
    
    @JsonIgnore
    @OneToMany(mappedBy = "tour")
    private List<DanhGia> danhGias;

    @JsonIgnore
    @OneToMany(mappedBy = "tour")
    private List<YeuThich> yeuThichs;

    @JsonIgnore
    @OneToMany(mappedBy = "tour")
    private List<GiamGia> giamGias;

    @JsonIgnore
    @OneToMany(mappedBy = "tour")
    private List<DanhSachNguoiDiCung> danhSachNguoiDiCungs;

    @JsonIgnore
    @OneToMany(mappedBy = "tour")
    private List<BienTheTour> bienTheTours;

    @JsonIgnore
    @OneToMany(mappedBy = "tour")
    private List<LichTrinhTour> lichTrinhTours;

    @JsonIgnore
    @OneToMany(mappedBy = "tour")
    private List<MediaTour> mediaTour;

}
