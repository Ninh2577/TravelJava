package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Entity.LichTrinhTour;

public interface LichTrinhTourRepository extends JpaRepository<LichTrinhTour, Integer>{

}
