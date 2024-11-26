package com.example.service;

import com.example.Entity.PhanQuyen;
import com.example.Entity.NguoiDung;
import com.example.Entity.ChucNang;
import com.example.Repository.PhanQuyenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhanQuyenService {

    @Autowired
    private PhanQuyenRepository phanQuyenRepository;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private ChucNangService chucNangService;

    // Cấp quyền cho người dùng
    public String capQuyen(String email, List<Integer> id_ChucNang) {
        Optional<NguoiDung> nguoiDung = nguoiDungService.findNguoiDungByEmail(email);
        if (!nguoiDung.isPresent()) {
            return "Người dùng không tồn tại";
        }

        for (Integer idChucNang : id_ChucNang) {
            ChucNang chucNang = chucNangService.getChucNangById(idChucNang);
            if (chucNang != null) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setNguoiDung(nguoiDung.get()); 
                phanQuyen.setChucNang(chucNang);  
                phanQuyenRepository.save(phanQuyen);  
            } else {
                return "Chức năng với ID " + idChucNang + " không tồn tại";
            }
        }

        return "Cấp quyền thành công";
    }

    // Lấy tất cả phân quyền
    public List<PhanQuyen> getAllPhanQuyen() {
        return phanQuyenRepository.findAll();
    }

    // Xóa phân quyền (optional)
    public void deleteAllPhanQuyenByUserId(Integer idNguoiDung) {
        List<PhanQuyen> phanQuyens = phanQuyenRepository.findByNguoiDungId(idNguoiDung);
        if (phanQuyens.isEmpty()) {
            throw new RuntimeException("Không có phân quyền nào để xóa cho người dùng với ID: " + idNguoiDung);
        }
        
        phanQuyenRepository.deleteAll(phanQuyens);
    }
    
    public List<Map<String, Object>> getNguoiDungWithChucNang() {
        List<NguoiDung> nguoiDungs = nguoiDungService.getAllNguoiDungs();
        List<PhanQuyen> phanQuyens = phanQuyenRepository.findAll();

        return nguoiDungs.stream().map(nguoiDung -> {
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", nguoiDung.getId());
            userData.put("hoTen", nguoiDung.getHoTen());
            userData.put("email", nguoiDung.getEmail());

            // Lọc các chức năng liên quan đến người dùng này
            List<String> chucNangNames = phanQuyens.stream()
                    .filter(phanQuyen -> phanQuyen.getNguoiDung().getId().equals(nguoiDung.getId()))
                    .map(phanQuyen -> phanQuyen.getChucNang().getTenChucNang()) // Lấy tên chức năng
                    .collect(Collectors.toList());

            userData.put("chucNang", String.join(", ", chucNangNames)); 
            return userData;
        }).collect(Collectors.toList());
    }

}
