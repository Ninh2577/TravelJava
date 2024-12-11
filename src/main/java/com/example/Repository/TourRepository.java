package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {
	// Thêm phương thức với @Query
	@Query("SELECT lt.loaiTour, t.tenTour " + "FROM Tour t " + "JOIN t.danhMucTour dt " + "JOIN t.loaiTour lt "
			+ "WHERE dt.id = :idDanhMucTour")
	List<Object[]> findToursByDanhMuc(@Param("idDanhMucTour") Integer idDanhMucTour);
	// Truy vấn tour theo danh mục tour 
	  @Query("SELECT t FROM Tour t JOIN t.danhMucTour d WHERE d.tenDanhMuc = :tenDanhMuc")
	    List<Tour> findToursByDanhMucTour(@Param("tenDanhMuc") String tenDanhMuc);


}