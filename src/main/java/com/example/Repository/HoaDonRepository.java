package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.ChiTietHoaDonDTO;
import com.example.DTO.DoanhThuDTO;
import com.example.DTO.SoLuongNguoiDiTour;
import com.example.Entity.HoaDon;

import java.util.Date;
import java.util.List;

//public interface HoaDonRepository extends JpaRepository<HoaDon, Integer>{
//
//}

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    List<HoaDon> findByNguoiDung_Id(Long userId);

    @Query("SELECT new com.example.DTO.ChiTietHoaDonDTO(cthd.id, cthd.ngayDat, t.tenTour, t.soLuongNguoi, cthd.thanhTien, hd.trangThai, hd.phuongThucThanhToan) "
            + "FROM HoaDon hd "
            + "JOIN ChiTietHoaDon cthd ON hd.id = cthd.hoaDon.id "
            + "JOIN BienTheTour bt ON cthd.bienTheTour.id = bt.id "
            + "JOIN Tour t ON bt.tour.id = t.id "
            + "WHERE hd.nguoiDung.id = :userId "
            + "ORDER BY cthd.ngayDat DESC")
    List<ChiTietHoaDonDTO> findHoaDonByUserId(@Param("userId") Integer userId);

    // Truy vấn tổng doanh thu theo ngày thanh toán trong khoảng thời gian
    @Query("SELECT hd.ngayThanhToan, SUM(hd.tongTien) " +
            "FROM HoaDon hd " +
            "WHERE hd.ngayThanhToan BETWEEN :startDate AND :endDate " +
            "AND hd.trangThai = true " +
            "GROUP BY hd.ngayThanhToan " +
            "ORDER BY hd.ngayThanhToan ASC")
    List<Object[]> findRevenueByDateRange(Date startDate, Date endDate);

    // tổng sl tour được đặt trong năm
    @Query("SELECT new com.example.DTO.DoanhThuDTO( "
            + "hd.ngayThanhToan, " // Ngày thanh toán
            + "COUNT(DISTINCT btt.id)) " // Đếm số lượng tour đã đặt (sử dụng DISTINCT để tránh đếm trùng)
            + "FROM HoaDon hd "
            + "JOIN ChiTietHoaDon cth ON hd.id = cth.hoaDon.id "
            + "JOIN BienTheTour btt ON cth.bienTheTour.id = btt.id "
            + "WHERE cth.trangThai = true " // Chỉ tính các hóa đơn đã thanh toán
            + "AND hd.ngayThanhToan BETWEEN :startDate AND :endDate " // Thống kê trong khoảng thời gian
            + "GROUP BY hd.ngayThanhToan " // Chỉ nhóm theo ngày thanh toán
            + "ORDER BY hd.ngayThanhToan ASC") // Sắp xếp theo ngày thanh toán
    List<DoanhThuDTO> findTourStatisticsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT new com.example.DTO.SoLuongNguoiDiTour( "
            + "hd.ngayThanhToan, "
            + "t.tenTour, "
            + "COUNT(DISTINCT dng.id)) "
            + "FROM HoaDon hd "
            + "JOIN ChiTietHoaDon cth ON hd.id = cth.hoaDon.id "
            + "JOIN BienTheTour btt ON cth.bienTheTour.id = btt.id "
            + "JOIN Tour t ON btt.tour.id = t.id " // Sửa thành btt.tour.id (trong thực thể BienTheTour)
            + "JOIN DanhSachNguoiDiCung dng ON cth.id = dng.chiTietHoaDon.id " // Cập nhật đúng cột khóa ngoại (thường
                                                                               // là cth.id)
            + "WHERE cth.trangThai = true "
            + "AND hd.ngayThanhToan BETWEEN :startDate AND :endDate "
            + "GROUP BY t.tenTour, hd.ngayThanhToan "
            + "ORDER BY hd.ngayThanhToan ASC")
    List<SoLuongNguoiDiTour> findTourStatisticsByDateRange2Dtos(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

}