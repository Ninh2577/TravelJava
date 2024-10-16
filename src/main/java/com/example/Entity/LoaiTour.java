package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LoaiTour")
public class LoaiTour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String loaiTour;
    private String moTa;
    @JsonIgnore
 	@OneToMany(mappedBy = "loaiTour")
 	private List<Tour> tours;
    
    @JsonCreator
    public LoaiTour(@JsonProperty("id") Integer id, 
                    @JsonProperty("loaiTour") String loaiTour, 
                    @JsonProperty("moTa") String moTa) {
        this.id = id;
        this.loaiTour = loaiTour;
        this.moTa = moTa;
    }
}
