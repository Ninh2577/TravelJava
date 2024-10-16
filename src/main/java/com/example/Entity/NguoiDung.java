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
@Table(name = "NguoiDung")
public class NguoiDung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hoTen;
    private String email;
    private String soDienThoai;
    private String diaChi;
    private String matKhau;
    private String hinhAnh;
    private boolean gioiTinh;
    private int tuoi;
    @Temporal(TemporalType.TIMESTAMP)
    private Date namSinh;
    private String cccd;
    private String anhCCCDtruoc;
    private String anhCCCDsau;
    
    @JsonIgnore
 	@OneToMany(mappedBy = "nguoiDung")
 	private List<GiamGia> giamGias;
    
    @JsonIgnore
 	@OneToMany(mappedBy = "nguoiDung")
 	private List<DanhGia> danhGias;
    
    @JsonIgnore
 	@OneToMany(mappedBy = "nguoiDung")
 	private List<ThanhToan> thanhToans;
    
    @JsonIgnore
 	@OneToMany(mappedBy = "nguoiDung")
 	private List<DatTour> datTours;
    
    @JsonIgnore
 	@OneToMany(mappedBy = "nguoiDung")
 	private List<Tour> tours;
    
    @JsonIgnore
 	@OneToMany(mappedBy = "nguoiDung")
 	private List<TinTuc> tinTucs;
    
    @ManyToOne
	@JoinColumn(name = "id_VaiTro")
	private VaiTro vaiTro;
    
}
