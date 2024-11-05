package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.BienTheTour;

public interface BienTheTourRepository extends JpaRepository<BienTheTour, Integer>{
	  List<BienTheTour> findByTourId(Integer tourId);
}
