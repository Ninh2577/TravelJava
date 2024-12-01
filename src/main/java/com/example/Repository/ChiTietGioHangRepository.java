package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.GioHangDTO;
import com.example.Entity.BienTheTour;
import com.example.Entity.ChiTietGioHang;
import com.example.Entity.MediaTour;
import com.example.Entity.NguoiDung;

public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer> {
        // @Query("SELECT c.nguoiDung.id FROM ChiTietGioHang c")
        // List<Integer> findAllNguoiDungIds();
        // @Query("SELECT c FROM ChiTietGioHang c WHERE c.bienTheTour.id =
        // :idBienTheTour")
        // List<ChiTietGioHang> findByChiTietGioHangId(@Param("idBienTheTour") Integer
        // idBienTheTour);
        @Query("SELECT c FROM ChiTietGioHang c WHERE c.bienTheTour.id = :idBienTheTour AND c.nguoiDung.id = :idNguoiDung")
        List<ChiTietGioHang> findByChiTietGioHangIdAndNguoiDungId(
                        @Param("idBienTheTour") Integer idBienTheTour,
                        @Param("idNguoiDung") Integer idNguoiDung);

        @Query("SELECT COUNT(c) > 0 FROM ChiTietGioHang c WHERE c.nguoiDung.id = :idNguoiDung AND c.bienTheTour.id = :idBienTheTour")
        boolean existsByIdNguoiDungAndIdBienTheTour(@Param("idNguoiDung") int idNguoiDung,
                        @Param("idBienTheTour") int idBienTheTour);

        List<ChiTietGioHang> findByNguoiDung(NguoiDung nguoiDung);

        ChiTietGioHang findByNguoiDungAndId(NguoiDung nguoiDung, Integer id);

        List<ChiTietGioHang> findByBienTheTour(BienTheTour bienTheTour);
        // List<ChiTietGioHang> findByNguoiDung(NguoiDung nguoiDung);

        // Truy vấn để lấy thông tin chi tiết giỏ hàng bao gồm tên tour, ngày bắt đầu,
        // ngày kết thúc, giá người lớn, giá trẻ em, số lượng tổng, và tổng tiền
        @Query("SELECT new com.example.DTO.GioHangDTO(" +
                        "ctgh.id, " +
                        "t.tenTour, " +
                        "bt.id, " +
                        "bt.ngayBatDau, " +
                        "bt.ngayKetThuc, " +
                        "bt.giaNguoiLon, " +
                        "bt.giaTreEm, " +
                        "ctgh.soNguoi, " +
                        "ctgh.tongTien, " +
                        "t.soNgay) " + // Thêm số ngày từ bảng Tour
                        "FROM ChiTietGioHang ctgh " +
                        "JOIN BienTheTour bt ON ctgh.bienTheTour.id = bt.id " +
                        "JOIN Tour t ON bt.tour.id = t.id " +
                        "WHERE ctgh.nguoiDung.id = :idNguoiDung")
        List<GioHangDTO> findCartDetailsByUserId(@Param("idNguoiDung") Integer idNguoiDung);

}
