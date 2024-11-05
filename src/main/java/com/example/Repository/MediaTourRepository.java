package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.MediaTour;

public interface MediaTourRepository extends JpaRepository<MediaTour, Integer>{
	List<MediaTour> findByTour_Id(Integer tourId);
}
