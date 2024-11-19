package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.BienTheTour;
import com.example.Entity.NguoiDung;
import com.example.Entity.Tour;
import com.example.Entity.YeuThich;
import com.example.Repository.NguoiDungRepository;
import com.example.Repository.TourRepository;
import com.example.Repository.YeuThichRepository;

@Service
public class YeuThichService {

    @Autowired
    private YeuThichRepository yeuThichRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private TourRepository tourRepository;

    public List<YeuThich> getAllYeuThich() {
        return yeuThichRepository.findAll();
    }
    
    public void deleteAllLikedToursByUserId(Integer userId) {
        yeuThichRepository.deleteAllByUserId(userId);
    }

//    public YeuThich saveYeuThich(Integer userId, Integer tourId) {
//        NguoiDung nguoiDung = nguoiDungRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Tour tour = tourRepository.findById(tourId)
//                .orElseThrow(() -> new RuntimeException("Tour not found"));
//
//        YeuThich existingYeuThich = yeuThichRepository.findByNguoiDungAndTour(nguoiDung, tour)
//                .orElse(null);
//        if (existingYeuThich != null) {
//            yeuThichRepository.delete(existingYeuThich);
//            return existingYeuThich; 
//        }
//
//        YeuThich yeuThich = new YeuThich();
//        yeuThich.setNguoiDung(nguoiDung);
//        yeuThich.setTour(tour);
//        yeuThich.setThich(true);
//
//        return yeuThichRepository.save(yeuThich);
//    }


  public YeuThich saveYeuThich(Integer userId, Integer tourId) {
      NguoiDung nguoiDung = nguoiDungRepository.findById(userId)
              .orElseThrow(() -> new RuntimeException("User not found"));
      Tour tour = tourRepository.findById(tourId)
              .orElseThrow(() -> new RuntimeException("Tour not found"));

      YeuThich existingYeuThich = yeuThichRepository.findByNguoiDungAndTour(nguoiDung, tour)
              .orElse(null);

      if (existingYeuThich != null) {
          existingYeuThich.setThich(!existingYeuThich.isThich());
          return yeuThichRepository.save(existingYeuThich);
      }

      // Nếu chưa yêu thích, tạo mới
      YeuThich yeuThich = new YeuThich();
      yeuThich.setNguoiDung(nguoiDung);
      yeuThich.setTour(tour);
      yeuThich.setThich(true);

      return yeuThichRepository.save(yeuThich);
  }
  
}

