package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.Entity.Tour;
import com.example.projection.TourDetailsProjection;

public interface TourRepository extends JpaRepository<Tour, Integer> {
}
