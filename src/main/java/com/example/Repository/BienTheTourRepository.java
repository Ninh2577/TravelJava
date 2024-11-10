package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.TourDetailsDTO;
import com.example.Entity.BienTheTour;

public interface BienTheTourRepository extends JpaRepository<BienTheTour, Integer> {
	List<BienTheTour> findByTourId(Integer tourId);

	 @Query("SELECT new com.example.DTO.TourDetailsDTO("
	            + "t.id, t.tenTour, t.hinhAnh, t.soNgay, b.ngayBatDau, "
	            + "b.giaNguoiLon, p.tenPhuongTien, h.danhGiaKhachSan) "
	            + "FROM BienTheTour b "
				+ "JOIN b.tour t " + "JOIN b.phuongTien p "
	            + "JOIN b.hotels h")
	    List<TourDetailsDTO> findAllTourInfo();
	 @Query("SELECT b FROM BienTheTour b WHERE b.tour.id = :idTour")
	    List<BienTheTour> findByToursId(@Param("idTour") Integer idTour);
}
