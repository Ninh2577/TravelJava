package com.example.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.TourDetailsDTO;
import com.example.Entity.BienTheTour;
import com.example.Entity.ChiTietGioHang;

public interface BienTheTourRepository extends JpaRepository<BienTheTour, Integer> {
	List<BienTheTour> findByTourId(Integer tourId);
	Optional<BienTheTour> findById(Integer id);
	 BienTheTour findByTourIdAndNgayBatDau(Integer tourId, Date ngayBatDau);
	 
	 @Query("SELECT new com.example.DTO.TourDetailsDTO("
		        + "t.id, t.tenTour, t.hinhAnh, t.soNgay, "
		        + "(SELECT MIN(bt.ngayBatDau) FROM BienTheTour bt WHERE bt.tour.id = t.id), "
		        + "(SELECT MIN(bt.giaNguoiLon) FROM BienTheTour bt WHERE bt.tour.id = t.id), "
		        + "(SELECT pt.tenPhuongTien FROM BienTheTour bt JOIN bt.phuongTien pt WHERE bt.tour.id = t.id AND bt.ngayBatDau = (SELECT MIN(bt2.ngayBatDau) FROM BienTheTour bt2 WHERE bt2.tour.id = t.id)), "
		        + "(SELECT h.danhGiaKhachSan FROM BienTheTour bt JOIN bt.hotels h WHERE bt.tour.id = t.id AND bt.ngayBatDau = (SELECT MIN(bt2.ngayBatDau) FROM BienTheTour bt2 WHERE bt2.tour.id = t.id))) "
		        + "FROM Tour t "
		        + "WHERE EXISTS (SELECT 1 FROM BienTheTour bt WHERE bt.tour.id = t.id)")
		List<TourDetailsDTO> findAllTourInfo();

	 @Query("SELECT b FROM BienTheTour b WHERE b.tour.id = :idTour")
	    List<BienTheTour> findByToursId(@Param("idTour") Integer idTour);
	
	    // Tìm biến thể tour theo tourId, ngày bắt đầu và ngày kết thúc
	    BienTheTour findByTourIdAndNgayBatDauAndNgayKetThuc(Integer tourId, Date ngayBatDau, Date ngayKetThuc);

  List<BienTheTour> findByChiTietGioHangs(List<ChiTietGioHang> chiTietGioHangs);
  @Query("SELECT new com.example.DTO.TourDetailsDTO(" +
	       "t.id, t.tenTour, t.hinhAnh, t.soNgay, b.ngayBatDau, " +
	       "b.giaNguoiLon, p.tenPhuongTien, h.danhGiaKhachSan) " +
	       "FROM BienTheTour b " +
	       "JOIN b.tour t " +
	       "JOIN b.phuongTien p " +
	       "JOIN b.hotels h " + // Note the space added here
	       "WHERE LOWER(t.tenTour) LIKE LOWER(CONCAT('%', :tenTour, '%'))")
	List<TourDetailsDTO> findAllByTenToursContaining(@Param("tenTour") String tenTour);
}
