package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.ChiTietHoaDonDTO;
import com.example.Entity.ChiTietHoaDon;

public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Integer> {
  
}
