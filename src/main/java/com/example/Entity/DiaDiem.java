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
@Table(name = "DiaDiem")
public class DiaDiem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phuong;
    private String quan;
    private String tinh;
    @ManyToOne
    @JoinColumn(name = "id_Tour")
    private Tour tour;
    private String viTri;
    private String moTa;
  
}
