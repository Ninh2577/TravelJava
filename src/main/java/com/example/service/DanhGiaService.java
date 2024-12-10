package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.DanhGia;
import com.example.Entity.MediaTouimport om.eimport com.example.Repository.DanhGiaRepository;

@Servicepublic la ss DanhGiaService {

	@Autowired
	private DanhGiaRepository danhGiaRepository;
	//GET Phương thức Đánh giá

    		retur danhGiaRepository.findAll();
	}
	
	 public List<DanhGia> getDanhGiarByTourId(Integer idTour) {
	        return danhGiaRepository.findByDanhGiaId(idTour);
	    }
	
	 public List<DanhGia> getAllTourByDanhGia(String tenTour){
		 return danhGiaRepository.findToursByDanhGia(tenTour);
	 }
}

     
     
     
     
     
     
     
     
     
     
     
     
    
     
     
     
     
    

    
    

     
     
        
    

    
        
    

     