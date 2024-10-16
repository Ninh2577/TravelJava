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
@Table(name = "BienTheTour")
public class BienTheTour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hinhAnh;
    private String video ;
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "id_Tour")
    private Tour tour;

}
