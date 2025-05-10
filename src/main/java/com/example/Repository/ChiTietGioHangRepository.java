package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.BienTheTour;
import com.example.Entity.ChiTietGioHang;
import com.example.Entity.MediaTour;
import com.example.Entity.NguoiDung;

public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer> {
//	  @Query("SELECT c.nguoiDung.id FROM ChiTietGioHang c")
//	    List<Integer> findAllNguoiDungIds();
	@Query("SELECT c FROM ChiTietGioHang c WHERE c.bienTheTour.id = :idBienTheTour")
	List<ChiTietGioHang> findByChiTietGioHangId(@Param("idBienTheTour") Integer idBienTheTour);

	@Query("SELECT COUNT(c) > 0 FROM ChiTietGioHang c WHERE c.nguoiDung.id = :idNguoiDung AND c.bienTheTour.id = :idBienTheTour")
	boolean existsByIdNguoiDungAndIdBienTheTour(@Param("idNguoiDung") int idNguoiDung,
			@Param("idBienTheTour") int idBienTheTour);
	
	List<ChiTietGioHang> findByNguoiDung(NguoiDung nguoiDung);

	ChiTietGioHang findByNguoiDungAndId(NguoiDung nguoiDung,Integer id);
	
	List<ChiTietGioHang> findByBienTheTour(BienTheTour bienTheTour);
//	   List<ChiTietGioHang> findByNguoiDung(NguoiDung nguoiDung);
}
