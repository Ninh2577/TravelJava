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
@Table(name = "TinTuc")
public class TinTuc implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_NguoiDung")
	private NguoiDung nguoiDung;
	private String noiDung;
	private String hinhAnh;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayDang;
	private String tieuDe;
	@ManyToOne
	@JoinColumn(name = "id_Tour")
	private Tour tour;
	private String video;
	private String moTa;

}
