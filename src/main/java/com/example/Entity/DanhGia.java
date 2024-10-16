package com.example.Entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DanhGia")
public class DanhGia implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_NguoiDung")
	private NguoiDung nguoiDung;
	@ManyToOne
	@JoinColumn(name = "id_DatTour")
	private DatTour datTour;
	private int danhGia;
	private String moTa;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngay;
}
