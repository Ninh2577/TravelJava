package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.PhanQuyen;
import com.example.Entity.ChucNang;
import com.example.Entity.NguoiDung;

import java.util.List;

@Repository
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, Integer> {

    // Tìm phân quyền của người dùng theo idNguoiDung
    List<PhanQuyen> findByNguoiDungId(Integer idNguoiDung); 
    // Tìm phân quyền của người dùng theo người dùng và chức năng 
    PhanQuyen findByNguoiDungAndChucNang(NguoiDung nguoiDung, ChucNang chucNang);
    // Tìm phân quyền của người dùng theo người dùng
    List<PhanQuyen> findByNguoiDung(NguoiDung nguoiDung);
}
