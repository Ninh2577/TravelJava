//package com.example.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.Entity.HoaDon;
//import com.example.Repository.HoaDonRepository;
//
//
//
//@Service
//public class HoaDonService {
//
//	@Autowired
//	private HoaDonRepository hoaDonRepository;
//	
//	// GET phương thức Hóa đơn 
//	public List<HoaDon> getAllHoaDon(){
//		return hoaDonRepository.findAll();
//	}
//	public List<HoaDon> findHoaDonByUserId(Long userId) {
//        return hoaDonRepository.findByNguoiDung_Id(userId);
//    }
//}

package com.example.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.DTO.ChiTietHoaDonsDTO;
import com.example.DTO.HoaDonDTO;
import com.example.Entity.BienTheTour;
import com.example.Entity.ChiTietGioHang;
import com.example.Entity.ChiTietHoaDon;
import com.example.Entity.DanhSachNguoiDiCung;
import com.example.Entity.GioHangDanhSachNguoiDiCung;
import com.example.Entity.HoaDon;
import com.example.Entity.NguoiDung;
import com.example.Repository.BienTheTourRepository;
import com.example.Repository.ChiTietGioHangRepository;
import com.example.Repository.ChiTietHoaDonRepository;
import com.example.Repository.DanhSachNguoiDiCungRepository;
import com.example.Repository.GioHangDanhSachNguoiDiCungRepository;
import com.example.Repository.HoaDonRepository;
import com.example.Repository.NguoiDungRepository;

@Service
public class HoaDonService {

	@Autowired
	private HoaDonRepository hoaDonRepository;

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private GioHangDanhSachNguoiDiCungRepository gioHangDanhSachNguoiDiCungRepository;

	@Autowired
	private DanhSachNguoiDiCungRepository danhSachNguoiDiCungRepository;

	@Autowired
	private ChiTietGioHangRepository chiTietGioHangRepository;

	@Autowired
	private ChiTietHoaDonRepository chiTietHoaDonRepository;

	@Autowired
	private BienTheTourRepository bienTheTourRepository;

	// GET phương thức Hóa đơn
	public List<HoaDon> getAllHoaDon() {
		return hoaDonRepository.findAll();
	}

	// POST phương thức thêm Hóa Đơn
	public HoaDon addHoaDon(HoaDon hoaDon) {
		return hoaDonRepository.save(hoaDon);
	}

	@Transactional
	public HoaDon addHoaDon(HoaDonDTO hoaDonDTO) {
		// Lấy thông tin người dùng (giả sử ID là 2 cho ví dụ này)
		NguoiDung nguoiDung = nguoiDungRepository.findById(2)
				.orElseThrow(() -> new IllegalArgumentException("NguoiDung với id 2 không được tìm thấy."));

		// Tạo và lưu HoaDon
		HoaDon hoaDon = new HoaDon();
		hoaDon.setNguoiDung(nguoiDung);
		hoaDon.setTongTien(hoaDonDTO.getTongTien());
		hoaDon.setNgayThanhToan(new Date());
		hoaDon.setPhuongThucThanhToan(hoaDonDTO.isPhuongThucThanhToan());
		hoaDon.setTrangThai(true); // Giả định 'true' đã chỉ định hóa đơn đang hoạt động

		HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);

		// Lấy ChiTietGioHang (Chi tiết giỏ hàng)
		ChiTietGioHang chiTietGioHang = chiTietGioHangRepository.findById(hoaDonDTO.getIdChiTietGioHang())
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy ChiTietGioHang với ID này."));

		// Kiểm tra xem người dùng hiện tại có quyền truy cập vào ChiTietGioHang
		if (!chiTietGioHang.getNguoiDung().getId().equals(hoaDonDTO.getIdNguoiDung())) {
			throw new SecurityException("Người dùng không có quyền truy cập vào ChiTietGioHang này.");
		}

		// Tạo và lưu ChiTietHoaDon
		ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
		chiTietHoaDon.setHoaDon(savedHoaDon); // Liên kết ChiTietHoaDon với HoaDon
		chiTietHoaDon.setBienTheTour(chiTietGioHang.getBienTheTour());
		chiTietHoaDon.setNgayDat(savedHoaDon.getNgayThanhToan());
		chiTietHoaDon.setTrangThai(savedHoaDon.isTrangThai());
		chiTietHoaDon.setThanhTien(savedHoaDon.getTongTien());
		chiTietHoaDon.setGiaNguoiLon(chiTietGioHang.getBienTheTour().getGiaNguoiLon());
		chiTietHoaDon.setGiaTreEm(chiTietGioHang.getBienTheTour().getGiaTreEm());
		chiTietHoaDon.setMoTa(savedHoaDon.isPhuongThucThanhToan() ? "Thanh toán trực tuyến" : "Tiền mặt");

		// Lưu ChiTietHoaDon
		chiTietHoaDonRepository.save(chiTietHoaDon);

		return savedHoaDon; // Trả về hóa đơn đã lưu
	}

	@Transactional
	public void huyHoaDon(Integer chiTietHoaDonId) {

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
		if (daysRemaining < 7) {
			throw new RuntimeException("Không thể hủy hóa đơn. Cần ít nhất 7 ngày trước ngày bắt đầu để hủy.");
		}

		// Lấy thông tin Chi Tiết Hóa Đơn
		ChiTietHoaDon chiTietHoaDon = chiTietHoaDonRepository.findById(chiTietHoaDonId)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết hóa đơn."));

		// Cập nhật trạng thái của HoaDon thành "Đã hủy"
		HoaDon hoaDon = chiTietHoaDon.getHoaDon();
		hoaDon.setTrangThai(false);
		hoaDonRepository.save(hoaDon);

		// Tăng số lượng còn lại trong Biến Thể Tour
		BienTheTour bienTheTour = chiTietHoaDon.getBienTheTour();
		bienTheTour.setSoLuongCon(bienTheTour.getSoLuongCon() + 1);
		bienTheTourRepository.save(bienTheTour);
	}

	public List<ChiTietHoaDonsDTO> getChiTietHoaDonById(Integer idHoaDon) {
        return hoaDonRepository.getHoaDonChiTietDanhSachNguoiDiCung(idHoaDon);
    }
}
