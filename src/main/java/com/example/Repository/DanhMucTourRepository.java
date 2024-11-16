package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Entity.DanhMucTour;
import com.example.Entity.Tour;

public interface DanhMucTourRepository extends JpaRepository<DanhMucTour, Integer> {

}
