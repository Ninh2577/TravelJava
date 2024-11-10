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
@Table(name = "BienTheTour")
public class BienTheTour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_Hotels")
    private Hotels hotels;

    @ManyToOne
    @JoinColumn(name = "id_PhuongTien")
    private PhuongTien phuongTien;

    @ManyToOne
    @JoinColumn(name = "id_GiamGia")
    private GiamGia giamGia;
    
    @JsonIgnore
    @OneToMany(mappedBy = "bienTheTour")
    private List<LichTrinhTour> lichTrinhTours;
    
    private float giaNguoiLon;
    private float giaTreEm;
    private String maTour;
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "id_Tour")	
    private Tour tour;

    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;

    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;

    private Integer soLuongCon;
    private Integer soLuongTong;
    
    @JsonIgnore 
    @OneToMany(mappedBy ="bienTheTour")
    private List<ChiTietGioHang> chiTietGioHangs;
}
