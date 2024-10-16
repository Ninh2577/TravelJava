package com.example.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ThanhToan")
public class ThanhToan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_NguoiDung")
    private NguoiDung nguoiDung;
    private String tenTour;
    private Double tongTien;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayThanhToan;
    private boolean phuongThucThanhToan;
    private boolean trangThai;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_DatTour")
    private DatTour datTour; // Thêm dòng này
}
