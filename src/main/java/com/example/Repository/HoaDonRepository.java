package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.ChiTietHoaDonDTO;
import com.example.Entity.HoaDon;

import java.util.List;

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
        List<HoaDon> findByNguoiDung_Id(Long userId);

        // @Query("SELECT new com.example.DTO.ChiTietHoaDonDTO(cthd.id, cthd.ngayDat,
        // t.tenTour, t.soLuongNguoi, cthd.thanhTien, hd.trangThai,
        // hd.phuongThucThanhToan) "
        // + "FROM HoaDon hd "
        // + "JOIN ChiTietHoaDon cthd ON hd.id = cthd.hoaDon.id "
        // + "JOIN BienTheTour bt ON cthd.bienTheTour.id = bt.id "
        // + "JOIN Tour t ON bt.tour.id = t.id "
        // + "WHERE hd.nguoiDung.id = :userId "
        // + "ORDER BY cthd.ngayDat DESC")
        // List<ChiTietHoaDonDTO> findHoaDonByUserId(@Param("userId") Integer userId);
        @Query("SELECT new com.example.DTO.ChiTietHoaDonDTO(cthd.id, cthd.ngayDat, t.tenTour, COUNT(dsndc.id), cthd.thanhTien, hd.trangThai, hd.phuongThucThanhToan) "
                        + "FROM HoaDon hd "
                        + "JOIN ChiTietHoaDon cthd ON hd.id = cthd.hoaDon.id "
                        + "JOIN BienTheTour bt ON cthd.bienTheTour.id = bt.id "
                        + "JOIN Tour t ON bt.tour.id = t.id "
                        + "JOIN DanhSachNguoiDiCung dsndc ON dsndc.chiTietHoaDon.id = cthd.id " // Đoạn này liên kết với
                                                                                                // bảng
                                                                                                // DanhSachNguoiDiCung
                        + "WHERE hd.nguoiDung.id = :userId "
                        + "GROUP BY cthd.id, cthd.ngayDat, t.tenTour, cthd.thanhTien, hd.trangThai, hd.phuongThucThanhToan "
                        + "ORDER BY cthd.ngayDat DESC")
        List<ChiTietHoaDonDTO> findHoaDonByUserId(@Param("userId") Integer userId);

}