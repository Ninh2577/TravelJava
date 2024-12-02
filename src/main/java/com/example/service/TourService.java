package com.example.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.ChiTietHoaDonsDTO;
import com.example.DTO.TourDetailsDTO;
import com.example.Entity.BienTheTour;
import com.example.Entity.ChiTietHoaDon;
import com.example.Entity.HoaDon;
import com.example.Entity.Tour;
import com.example.Repository.BienTheTourRepository;
import com.example.Repository.ChiTietGioHangRepository;
import com.example.Repository.ChiTietHoaDonRepository;
import com.example.Repository.HoaDonRepository;
import com.example.Repository.NguoiDungRepository;
import com.example.Repository.TourRepository;
import com.example.projection.TourDetailsProjection;

import jakarta.transaction.Transactional;

@Service
public class TourService {

	@Autowired
	private TourRepository tourRepository;
	@Autowired
	private BienTheTourRepository bienTheTourRepository;
	
	@Autowired
	private MailerService emailService;
	
	@Autowired
	private HoaDonRepository hoaDonRepository;

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private ChiTietGioHangRepository chiTietGioHangRepository;

	@Autowired
	private ChiTietHoaDonRepository chiTietHoaDonRepository;


	// Phương thức GET hết thông tin người dùng
	public List<Tour> getAllTours() {
		return tourRepository.findAll();
	}

	// Phương thức thêm tour mới
	public Tour addTour(Tour tour) {
		return tourRepository.save(tour); // Lưu tour mới vào database
	}

	public Optional<Tour> getTourById(Integer id) {
		return tourRepository.findById(id);
	}

	@Transactional
	public Tour updateTour(Integer id, Tour updatedTour) {
		Optional<Tour> existingTour = tourRepository.findById(id);
		if (existingTour.isPresent()) {
			Tour tour = existingTour.get();
			// Update fields
			tour.setDanhMucTour(updatedTour.getDanhMucTour());
			tour.setLoaiTour(updatedTour.getLoaiTour());
			tour.setTenTour(updatedTour.getTenTour());
			tour.setSoNgay(updatedTour.getSoNgay());
			tour.setMoTa(updatedTour.getMoTa());
			tour.setHinhAnh(updatedTour.getHinhAnh());
			tour.setSoLuongNguoi(updatedTour.getSoLuongNguoi());
			tour.setSoTour(updatedTour.getSoTour());
//			 if (tour.getSoTour() > 0) {
//			        tour.setTrangThai(true); // "Còn"
//			    } else {
//			        tour.setTrangThai(false); // "Hết"
//			    }
			tour.setTrangThai(updatedTour.isTrangThai());
			tour.setNoiDung(updatedTour.getNoiDung());
			tour.setDiemKhoiHanh(updatedTour.getDiemKhoiHanh());
			return tourRepository.save(tour);
		} else {
			return null;
		}
	}

	// Xóa Tour
	@Transactional
	public void deleteTour(Integer id) {
		Optional<Tour> optionalNguoiDung = tourRepository.findById(id);
		if (optionalNguoiDung.isPresent()) {
			tourRepository.deleteById(id); // Delete user by ID
		} else {
			throw new RuntimeException("Người dùng không tồn tại với ID: " + id);
		}
	}
	
	public List<TourDetailsDTO> getAllTourInfo() {
	    return bienTheTourRepository.findAllTourInfo(); 
	}



	public List<Object[]> getToursByDanhMuc(Integer idDanhMucTour) {
        return tourRepository.findToursByDanhMuc(idDanhMucTour);
    }
	
	public List<TourDetailsDTO> searchToursByName(String tenTour) {
        return bienTheTourRepository.findAllByTenToursContaining(tenTour);
    }
	
	@Transactional
	public void huyHoaDon(Integer chiTietHoaDonId, String cancelReason) {
		 System.out.println("id: " + chiTietHoaDonId);
		 System.out.println("Form admin:" + cancelReason);
	    // Lấy ngày bắt đầu từ cơ sở dữ liệu
	    LocalDate ngayBatDau = hoaDonRepository.findNgayBatDauByChiTietHoaDonId(chiTietHoaDonId);
	    System.out.println("Ngày bắt đầu: " + ngayBatDau);

	    // Kiểm tra ngày bắt đầu có hợp lệ hay không
	    if (ngayBatDau == null) {
	        throw new RuntimeException("Ngày bắt đầu không hợp lệ.");
	    }

	    // Tính số ngày còn lại từ ngày hiện tại đến ngày bắt đầu
	    long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), ngayBatDau);
	    System.out.println("ngày hiện tại: " + LocalDate.now());
	    System.out.println("Số ngày còn lại đến ngày bắt đầu: " + daysRemaining + " ngày.");

	    // Kiểm tra nếu số ngày còn lại nhỏ hơn 7
//	    if (daysRemaining < 7) {
//	        throw new RuntimeException("Không thể hủy hóa đơn. Cần ít nhất 7 ngày trước ngày bắt đầu để hủy.");
//	    }

	    // Lấy thông tin Chi Tiết Hóa Đơn
	    ChiTietHoaDon chiTietHoaDon = chiTietHoaDonRepository.findById(chiTietHoaDonId)
	            .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết hóa đơn."));

	    // Cập nhật trạng thái của HoaDon thành "Đã hủy"
	    HoaDon hoaDon = chiTietHoaDon.getHoaDon();
	    hoaDon.setTrangThai(false);
	    hoaDon.setGhiChu(cancelReason);
	    hoaDonRepository.save(hoaDon);
	    

	    // Tăng số lượng còn lại trong Biến Thể Tour
	    BienTheTour bienTheTour = chiTietHoaDon.getBienTheTour();
	    bienTheTour.setSoLuongCon(bienTheTour.getSoLuongCon() + 1);
	    bienTheTourRepository.save(bienTheTour);

	    // Gửi email thông báo hủy tour
	    String userName = hoaDon.getNguoiDung().getHoTen(); // Tên người dùng
	    String toEmail = hoaDon.getNguoiDung().getEmail(); // Địa chỉ email người dùng
	    float totalAmount = hoaDon.getTongTien(); // Tổng tiền hóa đơn
	    Date paymentDate = hoaDon.getNgayThanhToan(); // Ngày thanh toán
	    String tenTour = bienTheTour.getTour().getTenTour();

	    // Gọi phương thức gửi email
	    emailService.sendCancelTourEmail(toEmail, userName, totalAmount, paymentDate, cancelReason,tenTour);
	}

	public List<ChiTietHoaDonsDTO> getChiTietHoaDonById(Integer idHoaDon) {
        return hoaDonRepository.getHoaDonChiTietDanhSachNguoiDiCung(idHoaDon);
    }
	
	
}