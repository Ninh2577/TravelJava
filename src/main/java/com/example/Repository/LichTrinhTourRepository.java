package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.LichTrinhTour;

public interface LichTrinhTourRepository extends JpaRepository<LichTrinhTour, Integer>{
	@Query("SELECT l FROM LichTrinhTour l WHERE l.bienTheTour.id = :idBienTheTour")
    List<LichTrinhTour> findByBienTheTourId(@Param("idBienTheTour") Integer idBienTheTour);

}
