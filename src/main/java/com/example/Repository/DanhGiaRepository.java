package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.DanhGia;

public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {
	// @Query("SELECT d FROM DanhGia d WHERE d.tour.id = :idTour")
	// List<DanhGia> findByDanhGiaId(@Param("idTour") Integer idTour);
	@Query("SELECT d FROM DanhGia d WHERE d.tour.id = :idTour ORDER BY d.id DESC")
	List<DanhGia> findDanhGiaByTourId(@Param("idTour") Integer idTour);

	@Query("SELECT d FROM DanhGia d WHERE d.tour.id = :idTour")
	List<DanhGia> findByDanhGiaId(@Param("idTour") Integer idTour);

	// Truy vấn tour theo danh mục tour
	@Query("SELECT d FROM DanhGia d JOIN d.tour t WHERE t.tenTour = :tenTour")
	List<DanhGia> findToursByDanhGia(@Param("tenTour") String tenTour);
}