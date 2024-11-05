package com.example.Entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LichTrinhTour")
public class LichTrinhTour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_BienTheTour")
    private BienTheTour bienTheTour;

    private String tieuDe;
    private String noiDung;

    @Temporal(TemporalType.DATE)
    private Date ngay;

    private LocalTime thoiGianBatDau;
    private LocalTime thoiGianKetThuc;

    private String moTa;
}
