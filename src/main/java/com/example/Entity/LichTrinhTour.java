package com.example.Entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LichTrinhTour")
public class LichTrinhTour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tieuDe;
    private String noiDung;
    @ManyToOne
    @JoinColumn(name = "id_Tour")
    private Tour tour;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngay;
}
