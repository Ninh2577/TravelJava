package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DTO.DatTourDTO2;
import com.example.Entity.DatTour;

public interface DatTourRepository extends JpaRepository<DatTour, Integer> {
    // @Query("SELECT new com.example.DTO.DatTourDTO2(d.id, d.hoaDon.id,d.ngayDat,
    // d.trangThai, d.thanhTien, d.giaNguoiLon, d.giaTreEm, d.moTa) FROM DatTour d")
    // List<DatTourDTO2> findAllDatTourDTO();
    @Query("SELECT new com.example.DTO.DatTourDTO2(hd.id,d.ngayDat, d.trangThai, d.thanhTien, d.giaNguoiLon, d.giaTreEm, d.moTa) "
            +
            "FROM DatTour d JOIN d.hoaDon hd WHERE hd.nguoiDung.id = :userId")
    List<DatTourDTO2> findDatToursByUserId(@Param("userId") Integer userId);
}
