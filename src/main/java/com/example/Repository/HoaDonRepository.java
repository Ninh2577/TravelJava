package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.ChiTietHoaDonDTO;
import com.example.DTO.ChiTietHoaDonsDTO;
import com.example.Entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
        List<HoaDon> findByNguoiDung_Id(Long userId);

        @Query("SELECT new com.example.DTO.ChiTietHoaDonDTO(cthd.id, cthd.ngayDat, t.tenTour, COUNT(dsndc.id), cthd.thanhTien, hd.trangThai, hd.phuongThucThanhToan) "
                        + "FROM HoaDon hd "
                        + "JOIN ChiTietHoaDon cthd ON hd.id = cthd.hoaDon.id "
                        + "JOIN BienTheTour bt ON cthd.bienTheTour.id = bt.id "
                        + "JOIN Tour t ON bt.tour.id = t.id "
                        + "JOIN DanhSachNguoiDiCung dsndc ON dsndc.chiTietHoaDon.id = cthd.id "
                        + "WHERE hd.nguoiDung.id = :userId "
                        + "GROUP BY cthd.id, cthd.ngayDat, t.tenTour, cthd.thanhTien, hd.trangThai, hd.phuongThucThanhToan "
                        + "ORDER BY cthd.ngayDat DESC")
        List<ChiTietHoaDonDTO> findHoaDonByUserId(@Param("userId") Integer userId);

        // ----------------------------------------------------
        // cập nhật sl biển thể khi hủy
        @Query("SELECT bt.ngayBatDau FROM BienTheTour bt JOIN ChiTietHoaDon cthd ON cthd.bienTheTour.id = bt.id WHERE cthd.id = :chiTietHoaDonId")
        LocalDate findNgayBatDauByChiTietHoaDonId(@Param("chiTietHoaDonId") Integer chiTietHoaDonId);

        
        @Query("SELECT new com.example.DTO.ChiTietHoaDonsDTO( "
		                + "h.id, h.nguoiDung.id, h.tongTien, h.ngayThanhToan, h.phuongThucThanhToan, h.trangThai, "
		                + "c.id, c.ngayDat, c.trangThai, c.thanhTien, c.giaNguoiLon, c.giaTreEm, c.moTa, c.bienTheTour.id, "
		                + "d.id, d.hoTen, d.email, d.soDienThoai, d.namSinh, "
		                + "nguoiDung.hoTen, nguoiDung.soDienThoai, nguoiDung.email, nguoiDung.diaChi, "
		                + "b.tour.tenTour) "  // Thêm trường 'tenTour' từ bảng 'Tour'
		                + "FROM HoaDon h "
		                + "JOIN h.chiTietHoaDon c "
		                + "LEFT JOIN c.danhSachNguoiDiCungList d "
		                + "JOIN h.nguoiDung nguoiDung "
		                + "JOIN c.bienTheTour b "  // Thêm join để lấy thông tin từ 'BienTheTour'
		                + "JOIN b.tour t "  // Join vào bảng 'Tour' để lấy 'tenTour'
		                + "WHERE h.id = :idHoaDon")
		List<ChiTietHoaDonsDTO> getHoaDonChiTietDanhSachNguoiDiCung(@Param("idHoaDon") Integer idHoaDon);

}