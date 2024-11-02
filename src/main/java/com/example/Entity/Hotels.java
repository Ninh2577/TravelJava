package com.example.Entity;

import java.io.Serializable;
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
@Table(name = "Hotels")
public class Hotels implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tenKhachSan;
    private Integer danhGiaKhachSan;

    @JsonIgnore
    @OneToMany(mappedBy = "hotels")
    private List<BienTheTour> bienTheTours;
}
