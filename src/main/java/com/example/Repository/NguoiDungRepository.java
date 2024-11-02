package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer>{
	List<NguoiDung> findByVaiTroId(Integer vaiTroId);
	@Query("SELECT n FROM NguoiDung n WHERE n.vaiTro.id = :vaiTroId " + "AND (:hoTen IS NULL OR n.hoTen LIKE %:hoTen%) "
			+ "AND (:soDienThoai IS NULL OR n.soDienThoai LIKE %:soDienThoai%) "
			+ "AND (:email IS NULL OR n.email LIKE %:email%) " + "AND (:diaChi IS NULL OR n.diaChi LIKE %:diaChi%) "
			+ "AND (:tuoi IS NULL OR n.tuoi = :tuoi)")
	List<NguoiDung> searchNguoiDungByFields(@Param("vaiTroId") Integer vaiTroId, @Param("hoTen") String hoTen,
			@Param("soDienThoai") String soDienThoai, @Param("email") String email, @Param("diaChi") String diaChi,
			@Param("tuoi") Integer tuoi);
}
