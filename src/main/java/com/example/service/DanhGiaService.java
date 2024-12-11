package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entity.DanhGia;
import com.example.Repository.DanhGiaRepository;
@Service
 public class DanhGiaService {
    @Autowired
    private DanhGiaRepository danhGiaRepository;
    public List<DanhGia> getDanhGiarByTourId(Integer idTour) {
        return danhGiaRepository.findDanhGiaByTourId(idTour);
    }

    // Phương thức thêm đánh giá
    public DanhGia addDanhGia(DanhGia danhGia) {
        return danhGiaRepository.save(danhGia); // Lưu đánh giá vào cơ sở dữ liệu
    }

	//GET Phương thức Đánh giá
	public List<DanhGia> getAllDanhGia(){
		return danhGiaRepository.findAll();
	}
	

	
	 public List<DanhGia> getAllTourByDanhGia(String tenTour){
		 return danhGiaRepository.findToursByDanhGia(tenTour);
	 }
//    // Phương thức cập nhật đánh giá
//    public Optional<DanhGia> updateDanhGia(Integer id, DanhGia danhGiaDetails) {
//        return danhGiaRepository.findById(id).map(danhGia -> {
//            danhGia.setNguoiDung(danhGiaDetails.getNguoiDung());
//            danhGia.setTour(danhGiaDetails.getTour());
//            danhGia.setDanhGia(danhGiaDetails.getDanhGia());
//            danhGia.setMoTa(danhGiaDetails.getMoTa());
//            danhGia.setNgay(danhGiaDetails.getNgay());
//            danhGia.setHinhAnh(danhGiaDetails.getHinhAnh());
//            return danhGiaRepository.save(danhGia);
//        });
//    }
//
//    // Phương thức xóa đánh giá
//    public void deleteDanhGia(Integer id) {
//        danhGiaRepository.deleteById(id);
//    }
}
