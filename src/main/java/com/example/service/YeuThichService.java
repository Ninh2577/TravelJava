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
    
    // Phương thức này dùng để tìm biến thể tour có ngày bắt đầu khớp
    public BienTheTour selectBienTheTour(List<BienTheTour> bienTheTours, Date desiredStartDate) {
        for (BienTheTour bienTheTour : bienTheTours) {
            if (bienTheTour.getNgayBatDau().equals(desiredStartDate)) {
                return bienTheTour;  // Trả về biến thể tour có ngày đi trùng với desiredStartDate
            }
        }
        return null;  // Trả về null nếu không tìm thấy biến thể nào phù hợp
    }

    public YeuThich saveYeuThich(Integer userId, Integer tourId) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        // Kiểm tra xem người dùng đã yêu thích tour này chưa
        YeuThich existingYeuThich = yeuThichRepository.findByNguoiDungAndTour(nguoiDung, tour)
                .orElse(null);

        if (existingYeuThich != null) {
            // Nếu đã yêu thích, cập nhật lại trạng thái yêu thích
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

//package com.example.service;
//
//import com.example.Entity.BienTheTour;
//import com.example.Entity.NguoiDung;
//import com.example.Entity.Tour;
//import com.example.Entity.YeuThich;
//import com.example.Repository.BienTheTourRepository;
//import com.example.Repository.NguoiDungRepository;
//import com.example.Repository.TourRepository;
//import com.example.Repository.YeuThichRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class YeuThichService {
//
//    @Autowired
//    private YeuThichRepository yeuThichRepository;
//
//    @Autowired
//    private BienTheTourRepository bienTheTourRepository;
//
//    @Autowired
//    private NguoiDungRepository nguoiDungRepository;
//
//    @Autowired
//    private TourRepository tourRepository;
//
//    // Phương thức để lưu yêu thích với biến thể tour
//    public void saveYeuThich(Integer userId, Integer tourId, Date startDate, Date endDate) {
//        // Tìm biến thể tour dựa trên ngày bắt đầu và kết thúc
//        BienTheTour bienTheTour = bienTheTourRepository
//            .findByTourIdAndNgayBatDauAndNgayKetThuc(tourId, startDate, endDate);
//
//        if (bienTheTour == null) {
//            throw new RuntimeException("Không tìm thấy biến thể tour với ngày bắt đầu: " + startDate + 
//                                       " và ngày kết thúc: " + endDate);
//        }
//
//        // Lưu yêu thích
//        YeuThich yeuThich = new YeuThich();
//        yeuThich.setNguoiDung(new NguoiDung(userId)); // Tạo đối tượng NguoiDung
//        yeuThich.setTour(bienTheTour.getTour());     // Gắn Tour liên quan
//        yeuThich.setThich(true);                     // Đánh dấu yêu thích
//
//        yeuThichRepository.save(yeuThich);
//    }
//}
//
