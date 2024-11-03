package com.example.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NguoiDung")
public class NguoiDung implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_VaiTro")
    private VaiTro vaiTro;

    private String hoTen;
    private String email;
    private String soDienThoai;
    private String diaChi;
    private String matKhau;
    private String hinhAnh;
    private boolean gioiTinh;
    private Integer tuoi;

    @Temporal(TemporalType.DATE)
    private Date namSinh;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<HoaDon> hoaDons;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<BaiViet> baiViets;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<ChiTietGioHang> chiTietGioHangs;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<DanhSachNguoiDiCung> danhSachNguoiDiCungs;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<GiamGia> giamGias;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<YeuThich> yeuThichs;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<DanhGia> danhGias;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(vaiTro.getVaiTro())); // Giả sử vaiTro là một chuỗi.
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.matKhau; // Trả về mật khẩu của người dùng
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.email; // Trả về email của người dùng
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cần thêm logic nếu cần
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cần thêm logic nếu cần
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cần thêm logic nếu cần
    }

    @Override
    public boolean isEnabled() {
        return true; // Cần thêm logic nếu cần
    }
}
