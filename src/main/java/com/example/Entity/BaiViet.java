package com.example.Entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BaiViet")
public class BaiViet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_NguoiDung")
    private NguoiDung nguoiDung;

    private String noiDung;
    private String hinhAnh;

    @Temporal(TemporalType.DATE)
    private Date ngayDang;
    
    private String tieuDe;
    private String video;
    private String moTa;
}
