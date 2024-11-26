package com.example.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ChucNang")
public class ChucNang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tenChucNang;
    private String moTa;

    @JsonIgnore
    @OneToMany(mappedBy = "chucNang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhanQuyen> phanQuyens;

    public ChucNang(Integer id) {
        this.id = id;
    }

    public ChucNang() {
    }
}

