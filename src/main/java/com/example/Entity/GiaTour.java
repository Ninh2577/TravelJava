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
@Table(name = "GiaTour")
public class GiaTour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float giaNguoiLon;
    private float giaTreEm;
    
    @JsonIgnore
    @OneToMany(mappedBy = "giaTour") 
    private List<BienTheTour> bienTheTours;

}
