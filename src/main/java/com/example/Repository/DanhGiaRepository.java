package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.DanhGia;

public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer>{
	@Query("SELECT d FROM DanhGia d WHERE d.tour.id = :idTour")
    List<DanhGia> findByDanhGiaId(@Param("idTour") Integer idTour);
}
