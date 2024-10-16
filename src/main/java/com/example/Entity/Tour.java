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
@Table(name = "Tour")
public class Tour implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String tenTour;
	private Double giaTour;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayBatDau;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayKetThuc;
	private String moTa;
	private String hinhAnh;
	private String video;
	private int soLuongNguoi;
	private int soTour;
	@ManyToOne
	@JoinColumn(name = "id_LoaiTour")
	private LoaiTour loaiTour;
	private boolean trangThai;
	@ManyToOne
	@JoinColumn(name = "id_NguoiDung")
	private NguoiDung nguoiDung;
}
