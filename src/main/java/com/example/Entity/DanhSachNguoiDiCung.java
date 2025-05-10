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
    @ManyToOne // Assuming each person belongs to one cart
    @JoinColumn(name = "id_chitiethoadon") // Adjust this to the correct foreign key column name
    private ChiTietHoaDon chiTietHoaDon; 

    private String hoTen;
    private String email;
    private String soDienThoai;

    @Temporal(TemporalType.DATE)
    private Date namSinh;
   
}
