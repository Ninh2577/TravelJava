package com.example.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.DanhSachNguoiDiCungDTO;
import com.example.DTO.GioHangNguoiDiCungDTO;
import com.example.Entity.ChiTietGioHang;
import com.example.Entity.GioHangDanhSachNguoiDiCung;
import com.example.Entity.NguoiDung;

public interface GioHangDanhSachNguoiDiCungRepository extends JpaRepository<GioHangDanhSachNguoiDiCung, Integer> {
	Optional<GioHangDanhSachNguoiDiCung> findByEmail(String email);
	// List<GioHangDanhSachNguoiDiCung> findByChiTietGioHangId(int
	// chiTietGioHangId);
	@Query("SELECT g FROM GioHangDanhSachNguoiDiCung g WHERE g.chiTietGioHang.id = :idGioHangDanhSachNguoiDiCung")
	List<GioHangDanhSachNguoiDiCung> findByGioHangDanhSachNguoiDiCungId(
			@Param("idGioHangDanhSachNguoiDiCung") Integer idGioHangDanhSachNguoiDiCung);

	// List<GioHangDanhSachNguoiDiCung> findByChiTietGioHangId(ChiTietGioHang
	// chiTietGioHang);
	List<GioHangDanhSachNguoiDiCung> findByChiTietGioHang(ChiTietGioHang chiTietGioHang);

	int countByChiTietGioHangId(Integer chiTietGioHangId);

	@Query("SELECT new com.example.DTO.GioHangNguoiDiCungDTO(gndc.id,gndc.hoTen,gndc.email,gndc.soDienThoai, gndc.namSinh) "
			+ "FROM GioHangDanhSachNguoiDiCung gndc WHERE gndc.chiTietGioHang.id = :idChiTietGioHang")
	List<GioHangNguoiDiCungDTO> findDanhSachNguoiDiCung(@Param("idChiTietGioHang") Integer idChiTietGioHang);


	// --------------------------------------------
	void deleteByChiTietGioHangId(Long chiTietGioHangId);


}
