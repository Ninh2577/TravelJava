package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.HoaDon;
import java.util.List;

//public interface HoaDonRepository extends JpaRepository<HoaDon, Integer>{
//
//}

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    List<HoaDon> findByNguoiDung_Id(Long userId);
}