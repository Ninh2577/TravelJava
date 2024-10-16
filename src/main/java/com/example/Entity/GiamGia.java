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
@Table(name = "GiamGia")
public class GiamGia implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tieuDe;
	private String moTa;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayBatDau;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayKetThuc;
	private String noiDung;
	private String hinhAnh;
	@ManyToOne
	@JoinColumn(name = "id_NguoiDung")
	private NguoiDung nguoiDung;
	private Double tien;
	 @ManyToOne
	    @JoinColumn(name = "id_DatTour")
	    private DatTour datTour; // Thêm dòng này
}
