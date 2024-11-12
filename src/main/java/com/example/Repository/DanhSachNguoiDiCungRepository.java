package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.DanhSachNguoiDiCungDTO;
import com.example.Entity.DanhSachNguoiDiCung;

public interface DanhSachNguoiDiCungRepository extends JpaRepository<DanhSachNguoiDiCung, Integer> {

    @Query("SELECT new com.example.DTO.DanhSachNguoiDiCungDTO(dsndc.hoTen, dsndc.email, dsndc.soDienThoai, dsndc.namSinh) "
            +
            "FROM DanhSachNguoiDiCung dsndc " +
            "JOIN ChiTietHoaDon cthd ON dsndc.chiTietHoaDon.id = cthd.id " + // Dùng đúng tên trường `chiTietHoaDon.id`
            "JOIN HoaDon hd ON cthd.hoaDon.id = hd.id " + // Dùng đúng tên trường `hoaDon.id`
            "WHERE cthd.id = :chiTietHoaDonId AND hd.nguoiDung.id = :userId") // Kiểm tra lại trường `nguoiDung.id`
    List<DanhSachNguoiDiCungDTO> findNguoiDiCungByChiTietHoaDonIdAndUserId(
            @Param("chiTietHoaDonId") Integer chiTietHoaDonId,
            @Param("userId") Integer userId);

}
