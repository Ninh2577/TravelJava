package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Entity.ChiTietGioHang;

public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer>{
//	  @Query("SELECT c.nguoiDung.id FROM ChiTietGioHang c")
//	    List<Integer> findAllNguoiDungIds();
}
