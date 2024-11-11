package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.LichTrinhTour;
import com.example.Entity.MediaTour;

public interface MediaTourRepository extends JpaRepository<MediaTour, Integer>{
	List<MediaTour> findByTour_Id(Integer tourId);
	@Query("SELECT m FROM MediaTour m WHERE m.tour.id = :idTour")
    List<MediaTour> findByMediaTourId(@Param("idTour") Integer idTour);

}
