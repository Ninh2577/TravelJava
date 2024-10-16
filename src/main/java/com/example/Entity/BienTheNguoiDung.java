package com.example.Entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BienTheNguoiDung")
public class BienTheNguoiDung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hoTen;
    private String email;
    private String soDienThoai;
    @ManyToOne
    @JoinColumn(name = "id_DatTour")
    private DatTour datTour;
    private String cccd;
    @ManyToOne
    @JoinColumn(name = "id_NguoiDung")
    private NguoiDung nguoiDung;
    
 
}
