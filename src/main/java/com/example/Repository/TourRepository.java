package com.example.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.TourDetailsDTO;
import com.example.Entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {
	// Thêm phương thức với @Query
	// @Query("SELECT lt.loaiTour, t.tenTour " + "FROM Tour t " + "JOIN
	// t.danhMucTour dt " + "JOIN t.loaiTour lt "
	// + "WHERE dt.id = :idDanhMucTour")
	// List<Object[]> findToursByDanhMuc(@Param("idDanhMucTour") Integer
	// idDanhMucTour);

	// Truy vấn tour theo danh mục tour
	@Query("SELECT t FROM Tour t JOIN t.danhMucTour d WHERE d.tenDanhMuc = :tenDanhMuc")
	List<Tour> findToursByDanhMucTour(@Param("tenDanhMuc") String tenDanhMuc);

	@Query("SELECT t.id as tour_id, t.tenTour " +
			"FROM Tour t " +
			"JOIN t.danhMucTour dt " +
			// "JOIN t.loaiTour lt " +
			"WHERE dt.id = :idDanhMucTour")
	List<Object[]> findToursByDanhMuc(@Param("idDanhMucTour") Integer idDanhMucTour);

	@Query("SELECT new com.example.DTO.TourDetailsDTO(t.id, t.tenTour, t.soNgay, bt.ngayBatDau, bt.giaNguoiLon, t.hinhAnh) "
			+
			"FROM Tour t " +
			"JOIN t.danhMucTour dt " +
			"JOIN t.bienTheTours bt " +
			"WHERE dt.id = :idDanhMucTour " +
			"AND (:startDate IS NULL OR bt.ngayBatDau = :startDate)")
	List<TourDetailsDTO> findToursByDanhMucAndStartDate(
			@Param("idDanhMucTour") Integer idDanhMucTour,
			@Param("startDate") Date startDate);
}