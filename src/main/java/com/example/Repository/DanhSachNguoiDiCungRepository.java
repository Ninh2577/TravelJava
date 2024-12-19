package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.DanhSachNguoiDiCungDTO;
import com.example.Entity.DanhSachNguoiDiCung;

public interface DanhSachNguoiDiCungRepository extends JpaRepository<DanhSachNguoiDiCung, Integer> {

//    @Query("SELECT new com.example.DTO.DanhSachNguoiDiCungDTO(dsndc.hoTen, dsndc.email, dsndc.soDienThoai, dsndc.namSinh) "
//            +
//            "FROM DanhSachNguoiDiCung dsndc " +
//            "JOIN ChiTietHoaDon cthd ON dsndc.chiTietHoaDon.id = cthd.id " + // Dùng đúng tên trường `chiTietHoaDon.id`
//            "JOIN HoaDon hd ON cthd.hoaDon.id = hd.id " + // Dùng đúng tên trường `hoaDon.id`
//            "WHERE cthd.id = :chiTietHoaDonId AND hd.nguoiDung.id = :userId") // Kiểm tra lại trường `nguoiDung.id`
//    List<DanhSachNguoiDiCungDTO> findNguoiDiCungByChiTietHoaDonIdAndUserId(
//            @Param("chiTietHoaDonId") Integer chiTietHoaDonId,
//            @Param("userId") Integer userId);

	@Query("SELECT new com.example.DTO.DanhSachNguoiDiCungDTO(" +
	        "cthd.id, " +
	        "dsndc.hoTen, " +
	        "dsndc.email, " +
	        "dsndc.soDienThoai, " +
	        "dsndc.namSinh, " +
	        "bt.tour.tenTour, " +
			"bt.ngayBatDau, " +
	        "hd.trangThai, " +
	        "hd.phuongThucThanhToan, " +
	        "cthd.ngayDat, " +
	        "COUNT(dsndc.id), " +
	        "cthd.thanhTien) " +
	        "FROM DanhSachNguoiDiCung dsndc " +
	        "JOIN ChiTietHoaDon cthd ON dsndc.chiTietHoaDon.id = cthd.id " +
	        "JOIN HoaDon hd ON cthd.hoaDon.id = hd.id " +
	        "JOIN BienTheTour bt ON cthd.bienTheTour.id = bt.id " +
	        "JOIN Tour t ON bt.tour.id = t.id " +
	        "WHERE cthd.id = :chiTietHoaDonId " +
	        "AND hd.nguoiDung.id = :userId " +
	        "GROUP BY " +
	        "cthd.id, " + 
	        "dsndc.hoTen, " +
	        "dsndc.email, " +
	        "dsndc.soDienThoai, " +
	        "dsndc.namSinh, " +
	        "bt.tour.tenTour, " +
			"bt.ngayBatDau, " +
	        "hd.trangThai, " +
	        "hd.phuongThucThanhToan, " +
	        "cthd.ngayDat, " +
	        "cthd.thanhTien")
	List<DanhSachNguoiDiCungDTO> findNguoiDiCungByChiTietHoaDonIdAndUserId(
	        @Param("chiTietHoaDonId") Integer chiTietHoaDonId,
	        @Param("userId") Integer userId);

}
