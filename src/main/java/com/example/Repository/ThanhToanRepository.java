package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Entity.ThanhToan;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer>{
	@Query("SELECT t.id, t.nguoiDung.hoTen, t.nguoiDung.soDienThoai, t.nguoiDung.email, " +
		       "t.datTour.tour.tenTour, t.datTour.soNguoi, t.datTour.tongTien, t.datTour.tour.soLuongNguoi, " +
		       "t.ngayThanhToan, t.phuongThucThanhToan " +
		       "FROM ThanhToan t " +
		       "WHERE t.id = :thanhToanId")
		List<Object[]> getTourDatTourInfoByThanhToanId(Integer thanhToanId);

}
