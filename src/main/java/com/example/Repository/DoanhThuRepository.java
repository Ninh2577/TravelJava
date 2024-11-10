package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.ChiTietHoaDonDTO;
import com.example.DTO.DoanhThuDTO;
import com.example.DTO.DoanhThuNamDTO;
import com.example.DTO.DoanhThuNgayDTO;
import com.example.DTO.DoanhThuThangDTO;
import com.example.Entity.HoaDon;
import java.util.List;

public interface DoanhThuRepository extends JpaRepository<HoaDon, Integer> {
    // @Query("SELECT new com.example.DTO.DoanhThuDTO(YEAR(hd.ngayThanhToan),
    // lt.loaiTour, SUM(hd.tongTien), COUNT(hd.id)) "
    // +
    // "FROM HoaDon hd " +
    // "JOIN hd.chiTietHoaDon cthd " +
    // "JOIN cthd.bienTheTour btt " +
    // "JOIN btt.tour t " +
    // "JOIN t.loaiTour lt " +
    // "WHERE hd.trangThai = true AND YEAR(hd.ngayThanhToan) = :year " +
    // "GROUP BY YEAR(hd.ngayThanhToan), lt.loaiTour " +
    // "ORDER BY YEAR(hd.ngayThanhToan) DESC, SUM(hd.tongTien) DESC")
    // List<DoanhThuDTO> findDoanhThuByYear(@Param("year") int year);

    // @Query("SELECT new com.example.DTO.DoanhThuDTO(YEAR(hd.ngayThanhToan), lt.loaiTour, SUM(hd.tongTien), COUNT(hd.id)) "
    //         + "FROM HoaDon hd "
    //         + "JOIN hd.chiTietHoaDon cthd "
    //         + "JOIN cthd.bienTheTour btt "
    //         + "JOIN btt.tour t "
    //         + "JOIN t.loaiTour lt "
    //         + "WHERE hd.trangThai = true "
    //         + "AND YEAR(hd.ngayThanhToan) BETWEEN :startYear AND :endYear "
    //         + "GROUP BY YEAR(hd.ngayThanhToan), lt.loaiTour "
    //         + "ORDER BY YEAR(hd.ngayThanhToan) DESC, SUM(hd.tongTien) DESC")
    // List<DoanhThuDTO> findDoanhThuByYearRange(@Param("startYear") int startYear, @Param("endYear") int endYear);




    // Truy vấn lấy tất cả các năm có trong bảng HoaDon
    @Query("SELECT DISTINCT YEAR(hd.ngayThanhToan) FROM HoaDon hd WHERE hd.trangThai = true ORDER BY YEAR(hd.ngayThanhToan) ASC")
    List<Integer> findAllDistinctYears();

    // Truy vấn lấy tất cả các tháng  có trong bảng HoaDon
    @Query("SELECT DISTINCT MONTH(hd.ngayThanhToan) FROM HoaDon hd WHERE hd.trangThai = true ORDER BY YEAR(hd.ngayThanhToan) ASC")
    List<Integer> findAllDistinctMonths();

    //doanh thu năm
    @Query("SELECT new com.example.DTO.DoanhThuNamDTO(" +
            "YEAR(hd.ngayThanhToan), SUM(hd.tongTien)) " +
            "FROM HoaDon hd " +
            "WHERE hd.trangThai = true " +
            "AND YEAR(hd.ngayThanhToan) BETWEEN :startYear AND :endYear " +
            "GROUP BY YEAR(hd.ngayThanhToan) " +
            "ORDER BY YEAR(hd.ngayThanhToan) ASC")
    List<DoanhThuNamDTO> findDoanhThuByYearRange1Dtos(
            @Param("startYear") int startYear,
            @Param("endYear") int endYear);


    //danh thu tháng 
    @Query("SELECT new com.example.DTO.DoanhThuThangDTO(" +
            "MONTH(hd.ngayThanhToan), SUM(hd.tongTien)) " +
            "FROM HoaDon hd " +
            "WHERE hd.trangThai = true " +
            "AND YEAR(hd.ngayThanhToan) = :year " +
            "GROUP BY MONTH(hd.ngayThanhToan) " +
            "ORDER BY MONTH(hd.ngayThanhToan) ASC")
    List<DoanhThuThangDTO> findDoanhThuByMonthInYear(
            @Param("year") int year);

    //doanh thu ngày
    @Query("SELECT new com.example.DTO.DoanhThuNgayDTO(" +
        "DAY(hd.ngayThanhToan), SUM(hd.tongTien)) " +
        "FROM HoaDon hd " +
        "WHERE hd.trangThai = true " + // Kiểm tra trạng thái thanh toán
        "AND YEAR(hd.ngayThanhToan) = :selectedYear " + // Lọc theo năm người dùng chọn
        "AND MONTH(hd.ngayThanhToan) = :selectedMonth " + // Lọc theo tháng người dùng chọn
        "GROUP BY DAY(hd.ngayThanhToan) " +
        "ORDER BY DAY(hd.ngayThanhToan) ASC")
    List<DoanhThuNgayDTO> findDoanhThuByDay(@Param("selectedYear") int selectedYear, 
                                             @Param("selectedMonth") int selectedMonth);











    @Query("SELECT new com.example.DTO.DoanhThuThangDTO(" +
            "MONTH(hd.ngayThanhToan), SUM(hd.tongTien)) " +
            "FROM HoaDon hd " +
            "WHERE hd.trangThai = true " +
            "AND YEAR(hd.ngayThanhToan) = :year " +
            "GROUP BY MONTH(hd.ngayThanhToan) " +
            "ORDER BY MONTH(hd.ngayThanhToan) ASC")
    List<DoanhThuNamDTO> findDoanhThuByYearRangeAndMonth(
            @Param("year") int year);

}
