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
@Table(name = "GiamGia")
public class GiamGia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_Tour")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "id_NguoiDung")
    private NguoiDung nguoiDung;

    private String tieuDe;
    private String moTa;

    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;

    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;

    private String noiDung;
    private String hinhAnh;
    private Integer phanTram;
    private Integer soLuong;
    private String maGiamGia;
    
    @JsonIgnore
    @OneToMany(mappedBy = "giamGia")
    private List<BienTheTour> bienTheTours;
}
