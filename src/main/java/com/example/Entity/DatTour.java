package com.example.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DatTour")
public class DatTour implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_NguoiDung")
	private NguoiDung nguoiDung;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayDat;
	private boolean trangThai;
	private Double tongTien;
	@ManyToOne
	@JoinColumn(name = "id_Tour")
	private Tour tour;
	private String moTa;
	private Double tien;
	private Integer soNguoi;
	private Double tienGiam;
	private String QR;
	@JsonIgnore
	@OneToMany(mappedBy = "datTour")
	private List<BienTheNguoiDung> bienTheNguoiDungs;

	@JsonIgnore
	@OneToMany(mappedBy = "datTour")
	private List<GiamGia> giamGias;

	@JsonIgnore
	@OneToMany(mappedBy = "datTour")
	private List<ThanhToan> thanhToans;
	
	@JsonIgnore
	@OneToMany(mappedBy = "datTour")
	private List<DanhGia> danhGias;

}
