package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.ChiTietHoaDonDTO;
import com.example.DTO.DoanhThuDTO;
import com.example.Entity.HoaDon;
import java.util.List;

public interface DoanhThuRepository extends JpaRepository<HoaDon, Integer> {
    // @Query("SELECT new com.example.DTO.DoanhThuDTO(YEAR(hd.ngayThanhToan), lt.loaiTour, SUM(hd.tongTien), COUNT(hd.id)) "
    //         +
    //         "FROM HoaDon hd " +
    //         "JOIN hd.chiTietHoaDon cthd " +
    //         "JOIN cthd.bienTheTour btt " +
    //         "JOIN btt.tour t " +
    //         "JOIN t.loaiTour lt " +
    //         "WHERE hd.trangThai = true AND YEAR(hd.ngayThanhToan) = :year " +
    //         "GROUP BY YEAR(hd.ngayThanhToan), lt.loaiTour " +
    //         "ORDER BY YEAR(hd.ngayThanhToan) DESC, SUM(hd.tongTien) DESC")
    // List<DoanhThuDTO> findDoanhThuByYear(@Param("year") int year);

    @Query("SELECT new com.example.DTO.DoanhThuDTO(YEAR(hd.ngayThanhToan), lt.loaiTour, SUM(hd.tongTien), COUNT(hd.id)) "
        + "FROM HoaDon hd "
        + "JOIN hd.chiTietHoaDon cthd "
        + "JOIN cthd.bienTheTour btt "
        + "JOIN btt.tour t "
        + "JOIN t.loaiTour lt "
        + "WHERE hd.trangThai = true "
        + "AND YEAR(hd.ngayThanhToan) BETWEEN :startYear AND :endYear "
        + "GROUP BY YEAR(hd.ngayThanhToan), lt.loaiTour "
        + "ORDER BY YEAR(hd.ngayThanhToan) DESC, SUM(hd.tongTien) DESC")
    List<DoanhThuDTO> findDoanhThuByYearRange(@Param("startYear") int startYear, @Param("endYear") int endYear);

      // Truy vấn lấy tất cả các năm có trong bảng HoaDon
      @Query("SELECT DISTINCT YEAR(hd.ngayThanhToan) FROM HoaDon hd WHERE hd.trangThai = true ORDER BY YEAR(hd.ngayThanhToan) DESC")
      List<Integer> findAllDistinctYears();
//     @Query("SELECT new com.example.DTO.DoanhThuDTO(YEAR(hd.ngayThanhToan), SUM(hd.tongTien), COUNT(hd.id)) "
//         + "FROM HoaDon hd "
//         + "JOIN hd.chiTietHoaDon cthd "
//         + "JOIN cthd.bienTheTour btt "
//         + "JOIN btt.tour t "
//         + "WHERE hd.trangThai = true "
//         + "AND YEAR(hd.ngayThanhToan) BETWEEN :startYear AND :endYear "
//         + "GROUP BY YEAR(hd.ngayThanhToan) "
//         + "ORDER BY YEAR(hd.ngayThanhToan) DESC, SUM(hd.tongTien) DESC")
// List<DoanhThuDTO> findDoanhThuByYearRange(@Param("startYear") int startYear, @Param("endYear") int endYear);


}
