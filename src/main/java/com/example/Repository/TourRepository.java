package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer>{
	
}
