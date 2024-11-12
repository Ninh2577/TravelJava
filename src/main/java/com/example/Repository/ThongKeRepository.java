package com.example.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.DTO.DoanhThuTheoTour;
import com.example.DTO.SoLuongNguoiDiTour;
import com.example.DTO.SoLuongTourDaDatDTO;
import com.example.Entity.HoaDon;

@Repository
public interface ThongKeRepository extends JpaRepository<HoaDon, Integer> {

    // Thống kê tổng doanh thu theo khoản thời gian
    @Query("SELECT hd.ngayThanhToan, SUM(hd.tongTien) " +
            "FROM HoaDon hd " +
            "WHERE hd.ngayThanhToan BETWEEN :startDate AND :endDate " +
            "AND hd.trangThai = true " +
            "GROUP BY hd.ngayThanhToan " +
            "ORDER BY hd.ngayThanhToan ASC")
    List<Object[]> ThongKeTongDoanhThuTheoTG(@Param("startDate") Date ngayBatDau, 
                                             @Param("endDate") Date ngayKetThuc);

    // thống kê tổng số lượng tour được đặt trong khoảng thời gian
    @Query("SELECT new com.example.DTO.SoLuongTourDaDatDTO( "
            + "hd.ngayThanhToan, "
            + "COUNT(DISTINCT btt.id)) "
            + "FROM HoaDon hd "
            + "JOIN ChiTietHoaDon cth ON hd.id = cth.hoaDon.id "
            + "JOIN BienTheTour btt ON cth.bienTheTour.id = btt.id "
            + "WHERE cth.trangThai = true "
            + "AND hd.ngayThanhToan BETWEEN :ngayBatDau AND :ngayKetThuc "  // Đảm bảo tên tham số khớp
            + "GROUP BY hd.ngayThanhToan "
            + "ORDER BY hd.ngayThanhToan ASC")
    List<SoLuongTourDaDatDTO> ThongKeTourTheoKhoangThoiGian(@Param("ngayBatDau") Date ngayBatDau,
                                                     @Param("ngayKetThuc") Date ngayKetThuc);

    // Thống kê số lượng người tham gia mỗi tour
    @Query("SELECT new com.example.DTO.SoLuongNguoiDiTour( "
            + "hd.ngayThanhToan, "
            + "t.tenTour, "
            + "COUNT(DISTINCT dng.id)) "
            + "FROM HoaDon hd "
            + "JOIN ChiTietHoaDon cth ON hd.id = cth.hoaDon.id "
            + "JOIN BienTheTour btt ON cth.bienTheTour.id = btt.id "
            + "JOIN Tour t ON btt.tour.id = t.id "
            + "JOIN DanhSachNguoiDiCung dng ON cth.id = dng.chiTietHoaDon.id "
            + "WHERE cth.trangThai = true "
            + "AND hd.ngayThanhToan BETWEEN :ngayBatDau AND :ngayKetThuc "
            + "GROUP BY t.tenTour, hd.ngayThanhToan "
            + "ORDER BY hd.ngayThanhToan ASC")
    List<SoLuongNguoiDiTour> findThongKeSoLuongNguoiDiTourTheoKhoangThoiGian(@Param("ngayBatDau") Date ngayBatDau,
                                                                            @Param("ngayKetThuc") Date ngayKetThuc);


    // Thống kê doanh thu theo tour
    @Query("SELECT new com.example.DTO.DoanhThuTheoTour( "
            + "hd.ngayThanhToan, "
            + "t.tenTour, "
            + "SUM(cth.thanhTien)) "
            + "FROM HoaDon hd "
            + "JOIN ChiTietHoaDon cth ON hd.id = cth.hoaDon.id "
            + "JOIN BienTheTour btt ON cth.bienTheTour.id = btt.id "
            + "JOIN Tour t ON btt.tour.id = t.id "
            + "WHERE cth.trangThai = true "
            + "AND hd.ngayThanhToan BETWEEN :ngayBatDau AND :ngayKetThuc "
            + "GROUP BY t.tenTour, hd.ngayThanhToan "
            + "ORDER BY hd.ngayThanhToan ASC")
    List<DoanhThuTheoTour> findThongKeDoanhThuTheoTourTrongKhoangThoiGian(@Param("ngayBatDau") Date ngayBatDau,
                                                                           @Param("ngayKetThuc") Date ngayKetThuc);

    @Query("SELECT SUM(hd.tongTien) AS tongTien " +
    	       "FROM HoaDon hd " +
    	       "WHERE hd.ngayThanhToan BETWEEN :startDate AND :endDate " +
    	       "AND hd.trangThai = true")
    	Double calculateTotalAmountInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
