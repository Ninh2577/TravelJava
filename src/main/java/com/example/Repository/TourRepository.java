package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.Tour;
import com.example.projection.TourDetailsProjection;

public interface TourRepository extends JpaRepository<Tour, Integer> {
	@Query("SELECT t.tenTour AS tenTour, g.giaNguoiLon AS giaNguoiLon, "
			+ "t.soNgay AS soNgay, bt.ngayBatDau AS ngayBatDau, "
			+ "p.tenPhuongTien AS tenPhuongTien, h.danhGiaKhachSan AS danhGiaKhachSan " + "FROM Tour t "
			+ "JOIN t.bienTheTours bt " + "JOIN bt.giaTour g " + "JOIN bt.phuongTien p " + "JOIN bt.hotels h "
			+ "WHERE t.id = :tourId")
	List<TourDetailsProjection> findTourDetailsByTourId(@Param("tourId") Integer tourId);

	@Query("SELECT t.id AS id , t.hinhAnh AS hinhAnh , t.tenTour AS tenTour, g.giaNguoiLon AS giaNguoiLon, "
			+ "t.soNgay AS soNgay, bt.ngayBatDau AS ngayBatDau, "
			+ "p.tenPhuongTien AS tenPhuongTien, h.danhGiaKhachSan AS danhGiaKhachSan " + "FROM Tour t "
			+ "JOIN t.bienTheTours bt " + "JOIN bt.giaTour g " + "JOIN bt.phuongTien p " + "JOIN bt.hotels h")
	List<TourDetailsProjection> findAllTourDetails();

}
