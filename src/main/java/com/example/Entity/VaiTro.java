package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VaiTro")
public class VaiTro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String vaiTro;

    private String moTa;

    @JsonManagedReference // Quản lý vòng lặp JSON với NguoiDung
    @OneToMany(mappedBy = "vaiTro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NguoiDung> nguoiDungs;

    public VaiTro(Integer id) {
        this.id = id;
    }
}