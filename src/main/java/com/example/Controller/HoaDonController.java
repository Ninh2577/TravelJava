//package com.example.Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.Entity.HoaDon;
//import com.example.service.HoaDonService;
//import com.example.service.HotelsService;
//
//@RestController
//@RequestMapping("/api/hoadon")
//@CrossOrigin(origins = "http://localhost/3000")
//public class HoaDonController {
//
//	@Autowired
//	private HoaDonService hoaDonService;
//	
////	@GetMapping
////	public ResponseEntity<List<HoaDon>> getAllHoaDon(){
////		List<HoaDon> hoaDons = hoaDonService.getAllHoaDon();
////		return ResponseEntity.ok(hoaDons);
////	}
//	  @GetMapping("/user")
//	    public ResponseEntity<List<HoaDon>> getHoaDonsByUserId(@RequestParam Long userId) {
//	        List<HoaDon> hoaDons = hoaDonService.findHoaDonByUserId(userId);
//	        return ResponseEntity.ok(hoaDons);
//	    }
//}

package com.example.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.example.service.HoaDonService;
import com.example.service.MailerService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/hoadon")
@CrossOrigin(origins = "http://localhost:3000") // Cấu hình đúng CORS chỉ chấp nhận cors 3000

public class HoaDonController {

	@Autowired
	private HoaDonService hoaDonService;
	@Autowired
	private NguoiDungRepository nn;

	@Autowired
	private MailerService mailerService;

	@Autowired
	private ChiTietGioHangRepository chiTietGioHangRepository;
	@Autowired
	private BienTheTourRepository bienthetourRepository;
	@Autowired
	private ChiTietHoaDonRepository chiTietHoaDonRepository;
	@Autowired
	private HoaDonRepository hoaDonRepository;
	@Autowired
	private GioHangDanhSachNguoiDiCungRepository gioHangDanhSachNguoiDiCungRepository;
	@Autowired
	private DanhSachNguoiDiCungRepository danhSachNguoiDiCungRepository;

	// ChiTietGioHangController chiTietGioHangController;
	private HttpServletRequest request;

	@GetMapping
	public ResponseEntity<List<HoaDon>> getAllHoaDon() {
		List<HoaDon> hoaDons = hoaDonService.getAllHoaDon();
		return ResponseEntity.ok(hoaDons);
	}

	@PostMapping("/them")
	public HoaDon addHoaDon(@RequestBody HoaDonDTO hoaDonDTO) {

		return hoaDonService.addHoaDon(hoaDonDTO);
	}

	// @PostMapping("/them2/{id}")
	// public String addHoaDon2(@PathVariable("id") Integer id,
	// @RequestParam("idNguoiDung") Integer idNguoiDung,
	// @RequestBody HoaDonDTO hoaDonDTO) {
	// System.out.println("userID: " + idNguoiDung);
	// // BienTheTour bienthetour =
	// // bienthetourRepository.findById(hoaDonDTO.getIdBienTheTour()).get();
	// // BienTheTour bienthetour =
	// // bienthetourRepository.findById(bienTheTour.getId()).get();
	// ChiTietGioHang ctg1h = chiTietGioHangRepository.findById(id).get();
	// NguoiDung nguoiDung = nn.findById(idNguoiDung).get();// 2
	// HoaDon hoadon = new HoaDon();
	// hoadon.setNguoiDung(nguoiDung);
	// hoadon.setTongTien(hoaDonDTO.getTongTien()); // Example total price
	// hoadon.setNgayThanhToan(new Date());
	// hoadon.setPhuongThucThanhToan(true); // Example payment method (true for
	// online)
	// hoadon.setTrangThai(true); // Assuming true for a completed transaction
	// HoaDon savedHoaDon = hoaDonRepository.save(hoadon); // Saving HoaDon
	// BienTheTour bienthetour =
	// bienthetourRepository.findById(hoaDonDTO.getIdBienTheTour()).orElse(null);
	// if (bienthetour == null) {
	// return "BienTheTour not found"; // Trả về lỗi nếu không tìm thấy
	// }
	// ChiTietHoaDon chitiethoadon = new ChiTietHoaDon();
	// chitiethoadon.setHoaDon(savedHoaDon); // Associate with the saved HoaDon
	// chitiethoadon.setBienTheTour(bienthetour); // Set BienTheTour object
	// chitiethoadon.setNgayDat(savedHoaDon.getNgayThanhToan());
	// chitiethoadon.setTrangThai(true);
	// chitiethoadon.setThanhTien(savedHoaDon.getTongTien());
	// chitiethoadon.setGiaNguoiLon(bienthetour.getGiaNguoiLon());
	// chitiethoadon.setGiaTreEm(bienthetour.getGiaTreEm());
	// chitiethoadon.setMoTa(bienthetour.getMaTour());
	// chiTietHoaDonRepository.save(chitiethoadon); //

	// // Lấy danh sách ChiTietGioHang của BienTheTour
	// // List<ChiTietGioHang> ctgh =
	// // chiTietGioHangRepository.findByBienTheTour(bienthetour);
	// // Tìm ChiTietGioHang cụ thể của NguoiDung bằng idChiTietGioHang từ hoaDonDTO
	// // ChiTietGioHang ctg1h =
	// // chiTietGioHangRepository.findByNguoiDungAndId(nguoiDung,
	// // hoaDonDTO.getIdChiTietGioHang());
	// // if (ctg1h == null) {
	// // return "ChiTietGioHang not found";
	// // }

	// // Lấy danh sách người đi cùng từ ChiTietGioHang
	// List<GioHangDanhSachNguoiDiCung> listGhdsndc =
	// gioHangDanhSachNguoiDiCungRepository.findByChiTietGioHang(ctg1h);
	// // System.out.println("Số lượng người đi cùng: " + listGhdsndc.size());

	// // Lưu thông tin vào DanhSachNguoiDiCung
	// for (GioHangDanhSachNguoiDiCung gioHangDanhSachNguoiDiCung : listGhdsndc) {

	// DanhSachNguoiDiCung danhSachNguoiDiCung = new DanhSachNguoiDiCung();
	// danhSachNguoiDiCung.setChiTietHoaDon(chitiethoadon); // Liên kết với
	// ChiTietHoaDon đã lưu
	// danhSachNguoiDiCung.setHoTen(gioHangDanhSachNguoiDiCung.getHoTen());
	// danhSachNguoiDiCung.setEmail(gioHangDanhSachNguoiDiCung.getEmail());
	// danhSachNguoiDiCung.setSoDienThoai(gioHangDanhSachNguoiDiCung.getSoDienThoai());
	// danhSachNguoiDiCung.setNamSinh(gioHangDanhSachNguoiDiCung.getNamSinh());
	// danhSachNguoiDiCungRepository.save(danhSachNguoiDiCung); // Lưu thông tin
	// người đi cùng
	// }

	// // Cập nhật số lượng còn lại của BienTheTour
	// int soLuongGioHang = ctg1h.getSoNguoi(); // Số lượng từ giỏ hàng
	// int soLuongHienTai = bienthetour.getSoLuongCon(); // Số lượng hiện tại của
	// BienTheTour
	// bienthetour.setSoLuongCon(soLuongHienTai - soLuongGioHang);
	// bienthetourRepository.save(bienthetour); // Cập nhật lại BienTheTour với số
	// lượng còn lại

	// // Xóa danh sách người đi cùng khỏi giỏ hàng và xóa ChiTietGioHang sau khi
	// lưu
	// gioHangDanhSachNguoiDiCungRepository.deleteAll(listGhdsndc);
	// chiTietGioHangRepository.delete(ctg1h);

	// return "oke"; // Trả về thành công

	// }

	@PostMapping("/them2/{id}")
	public ResponseEntity<?> addHoaDon2(@PathVariable("id") Integer id,
			@RequestParam("idNguoiDung") Integer idNguoiDung,
			@RequestBody HoaDonDTO hoaDonDTO) throws MessagingException {
		System.out.println("userID: " + idNguoiDung);

		ChiTietGioHang ctg1h = chiTietGioHangRepository.findById(id).get();
		NguoiDung nguoiDung = nn.findById(idNguoiDung).get();
		HoaDon hoadon = new HoaDon();
		hoadon.setNguoiDung(nguoiDung);
		hoadon.setTongTien(hoaDonDTO.getTongTien());
		hoadon.setNgayThanhToan(new Date());
		hoadon.setPhuongThucThanhToan(true);
		hoadon.setTrangThai(true);
		HoaDon savedHoaDon = hoaDonRepository.save(hoadon);

		BienTheTour bienthetour = bienthetourRepository.findById(hoaDonDTO.getIdBienTheTour()).orElse(null);
		if (bienthetour == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BienTheTour not found");
		}

		ChiTietHoaDon chitiethoadon = new ChiTietHoaDon();
		chitiethoadon.setHoaDon(savedHoaDon);
		chitiethoadon.setBienTheTour(bienthetour);
		chitiethoadon.setNgayDat(savedHoaDon.getNgayThanhToan());
		chitiethoadon.setTrangThai(true);
		chitiethoadon.setThanhTien(savedHoaDon.getTongTien());
		chitiethoadon.setGiaNguoiLon(bienthetour.getGiaNguoiLon());
		chitiethoadon.setGiaTreEm(bienthetour.getGiaTreEm());
		chitiethoadon.setMoTa(bienthetour.getMaTour());
		chiTietHoaDonRepository.save(chitiethoadon);

		List<GioHangDanhSachNguoiDiCung> listGhdsndc = gioHangDanhSachNguoiDiCungRepository.findByChiTietGioHang(ctg1h);
		for (GioHangDanhSachNguoiDiCung gioHangDanhSachNguoiDiCung : listGhdsndc) {
			DanhSachNguoiDiCung danhSachNguoiDiCung = new DanhSachNguoiDiCung();
			danhSachNguoiDiCung.setChiTietHoaDon(chitiethoadon);
			danhSachNguoiDiCung.setHoTen(gioHangDanhSachNguoiDiCung.getHoTen());
			danhSachNguoiDiCung.setEmail(gioHangDanhSachNguoiDiCung.getEmail());
			danhSachNguoiDiCung.setSoDienThoai(gioHangDanhSachNguoiDiCung.getSoDienThoai());
			danhSachNguoiDiCung.setNamSinh(gioHangDanhSachNguoiDiCung.getNamSinh());
			danhSachNguoiDiCungRepository.save(danhSachNguoiDiCung);
		}

		int soLuongGioHang = ctg1h.getSoNguoi();
		int soLuongHienTai = bienthetour.getSoLuongCon();
		bienthetour.setSoLuongCon(soLuongHienTai - soLuongGioHang);
		bienthetourRepository.save(bienthetour);

		gioHangDanhSachNguoiDiCungRepository.deleteAll(listGhdsndc);
		chiTietGioHangRepository.delete(ctg1h);

		// Gọi phương thức gửi email hóa đơn sau khi tạo hóa đơn thành công
		CompletableFuture.runAsync(() -> {
			try {
				mailerService.sendInvoiceEmail(savedHoaDon.getId());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
		// Trả về thông tin chi tiết về hóa đơn và ID hóa đơn
		return ResponseEntity.ok(new HashMap<String, Object>() {
			{
				put("status", "success");
				put("paymentId", savedHoaDon.getId());
				put("message", "Hóa đơn đã được tạo thành công");
			}
		});
	}

	// @GetMapping("/api/vnpay/callback/{id}/{idNguoiDung}")
	// public ResponseEntity<String> vnpayCallbackAndCreateHoaDon(@PathVariable("id") Integer id,
	// 		@PathVariable("idNguoiDung") Integer idNguoiDung,
	// 		@RequestParam Map<String, String> vnpayParams) {
	// 	try {
	// 		// Check if id is valid
	// 		if (id == null || id <= 0) {
	// 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid order ID.");
	// 		}

	// 		// Log for debugging
	// 		System.out.println("Received callback with ID: " + id);
	// 		System.out.println("vnpayParams: " + vnpayParams);

	// 		// Retrieve payment data from the callback
	// 		float totalTien = Float.parseFloat(vnpayParams.get("vnp_Amount")) / 100; // Convert from VND to float

	// 		// Find ChiTietGioHang using the id passed from the callback
	// 		ChiTietGioHang ctg1h = chiTietGioHangRepository.findById(id).orElse(null);
	// 		if (ctg1h == null) {
	// 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ChiTietGioHang not found for id: " + id);
	// 		}

	// 		// Fetch NguoiDung and other details
	// 		NguoiDung nguoiDung = nn.findById(idNguoiDung).get(); // Hardcoded for now, adjust as necessary
	// 		HoaDon hoadon = new HoaDon();
	// 		hoadon.setNguoiDung(nguoiDung);
	// 		hoadon.setTongTien(totalTien); // Set total price from the callback
	// 		hoadon.setNgayThanhToan(new Date());
	// 		hoadon.setPhuongThucThanhToan(false); // true = 1 là thanh toán = tiền mặt , false =0 là thanh toán = VNPAY
	// 		hoadon.setTrangThai(true); // Payment completed
	// 		HoaDon savedHoaDon = hoaDonRepository.save(hoadon);

	// 		// Handle BienTheTour
	// 		BienTheTour bienthetour = bienthetourRepository.findById(ctg1h.getBienTheTour().getId()).orElse(null);
	// 		if (bienthetour == null) {
	// 			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BienTheTour not found");
	// 		}

	// 		// Create and save ChiTietHoaDon
	// 		ChiTietHoaDon chitiethoadon = new ChiTietHoaDon();
	// 		chitiethoadon.setHoaDon(savedHoaDon); // Link to saved HoaDon
	// 		chitiethoadon.setBienTheTour(bienthetour); // Set BienTheTour
	// 		chitiethoadon.setNgayDat(savedHoaDon.getNgayThanhToan());
	// 		chitiethoadon.setTrangThai(true);
	// 		chitiethoadon.setThanhTien(savedHoaDon.getTongTien());
	// 		chitiethoadon.setGiaNguoiLon(bienthetour.getGiaNguoiLon());
	// 		chitiethoadon.setGiaTreEm(bienthetour.getGiaTreEm());
	// 		chitiethoadon.setMoTa(bienthetour.getMaTour());
	// 		chiTietHoaDonRepository.save(chitiethoadon);

	// 		// Save DanhSachNguoiDiCung (passenger list)
	// 		List<GioHangDanhSachNguoiDiCung> listGhdsndc = gioHangDanhSachNguoiDiCungRepository
	// 				.findByChiTietGioHang(ctg1h);
	// 		for (GioHangDanhSachNguoiDiCung gioHangDanhSachNguoiDiCung : listGhdsndc) {
	// 			DanhSachNguoiDiCung danhSachNguoiDiCung = new DanhSachNguoiDiCung();
	// 			danhSachNguoiDiCung.setChiTietHoaDon(chitiethoadon); // Link with ChiTietHoaDon
	// 			danhSachNguoiDiCung.setHoTen(gioHangDanhSachNguoiDiCung.getHoTen());
	// 			danhSachNguoiDiCung.setEmail(gioHangDanhSachNguoiDiCung.getEmail());
	// 			danhSachNguoiDiCung.setSoDienThoai(gioHangDanhSachNguoiDiCung.getSoDienThoai());
	// 			danhSachNguoiDiCung.setNamSinh(gioHangDanhSachNguoiDiCung.getNamSinh());
	// 			danhSachNguoiDiCungRepository.save(danhSachNguoiDiCung);
	// 		}

	// 		// Update BienTheTour stock
	// 		int soLuongGioHang = ctg1h.getSoNguoi(); // Get quantity from cart
	// 		int soLuongHienTai = bienthetour.getSoLuongCon(); // Get current stock
	// 		bienthetour.setSoLuongCon(soLuongHienTai - soLuongGioHang);
	// 		bienthetourRepository.save(bienthetour); // Update stock

	// 		// Delete related cart items after processing
	// 		gioHangDanhSachNguoiDiCungRepository.deleteAll(listGhdsndc);
	// 		chiTietGioHangRepository.delete(ctg1h);
	// 		// Gọi phương thức gửi email hóa đơn sau khi tạo hóa đơn thành công
	// 		CompletableFuture.runAsync(() -> {
	// 			try {
	// 				mailerService.sendInvoiceEmail(savedHoaDon.getId());
	// 			} catch (MessagingException e) {
	// 				e.printStackTrace();
	// 			}
	// 		});
	// 		// Redirect to frontend on success
	// 		return ResponseEntity.status(HttpStatus.FOUND)
	// 				.header("Location", "http://localhost:3000" + hoadon.getId())
	// 				.body("Payment processed and redirected to frontend...");

	// 	} catch (Exception e) {
	// 		// Handle any errors
	// 		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
	// 	}

	// }
	@GetMapping("/api/vnpay/callback/{id}/{idNguoiDung}")
public ResponseEntity<String> vnpayCallbackAndCreateHoaDon(@PathVariable("id") Integer id,
        @PathVariable("idNguoiDung") Integer idNguoiDung,
        @RequestParam Map<String, String> vnpayParams) {
    try {
        // Kiểm tra xem id có hợp lệ không
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid order ID.");
        }

        // Log thông tin callback
        System.out.println("Received callback with ID: " + id);
        System.out.println("vnpayParams: " + vnpayParams);

        // Lấy dữ liệu thanh toán từ callback
        float totalTien = Float.parseFloat(vnpayParams.get("vnp_Amount")) / 100; // Chuyển đổi từ VND sang float

        // Kiểm tra mã phản hồi từ VNPAY
        String vnp_ResponseCode = vnpayParams.get("vnp_ResponseCode");

        // Nếu mã phản hồi là "00" thì thanh toán thành công
        if ("00".equals(vnp_ResponseCode)) {
            // Tiến hành tạo hóa đơn
            ChiTietGioHang ctg1h = chiTietGioHangRepository.findById(id).orElse(null);
            if (ctg1h == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ChiTietGioHang not found for id: " + id);
            }

            // Fetch người dùng và thông tin khác
            NguoiDung nguoiDung = nn.findById(idNguoiDung).get();
            HoaDon hoadon = new HoaDon();
            hoadon.setNguoiDung(nguoiDung);
            hoadon.setTongTien(totalTien);
            hoadon.setNgayThanhToan(new Date());
            hoadon.setPhuongThucThanhToan(false); // false = VNPAY
            hoadon.setTrangThai(true); // Thanh toán thành công
            HoaDon savedHoaDon = hoaDonRepository.save(hoadon);

            // Handle BienTheTour
            BienTheTour bienthetour = bienthetourRepository.findById(ctg1h.getBienTheTour().getId()).orElse(null);
            if (bienthetour == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BienTheTour not found");
            }

            // Tạo ChiTietHoaDon
            ChiTietHoaDon chitiethoadon = new ChiTietHoaDon();
            chitiethoadon.setHoaDon(savedHoaDon);
            chitiethoadon.setBienTheTour(bienthetour);
            chitiethoadon.setNgayDat(savedHoaDon.getNgayThanhToan());
            chitiethoadon.setTrangThai(true);
            chitiethoadon.setThanhTien(savedHoaDon.getTongTien());
            chitiethoadon.setGiaNguoiLon(bienthetour.getGiaNguoiLon());
            chitiethoadon.setGiaTreEm(bienthetour.getGiaTreEm());
            chitiethoadon.setMoTa(bienthetour.getMaTour());
            chiTietHoaDonRepository.save(chitiethoadon);

            // Lưu DanhSachNguoiDiCung
            List<GioHangDanhSachNguoiDiCung> listGhdsndc = gioHangDanhSachNguoiDiCungRepository.findByChiTietGioHang(ctg1h);
            for (GioHangDanhSachNguoiDiCung gioHangDanhSachNguoiDiCung : listGhdsndc) {
                DanhSachNguoiDiCung danhSachNguoiDiCung = new DanhSachNguoiDiCung();
                danhSachNguoiDiCung.setChiTietHoaDon(chitiethoadon);
                danhSachNguoiDiCung.setHoTen(gioHangDanhSachNguoiDiCung.getHoTen());
                danhSachNguoiDiCung.setEmail(gioHangDanhSachNguoiDiCung.getEmail());
                danhSachNguoiDiCung.setSoDienThoai(gioHangDanhSachNguoiDiCung.getSoDienThoai());
                danhSachNguoiDiCung.setNamSinh(gioHangDanhSachNguoiDiCung.getNamSinh());
                danhSachNguoiDiCungRepository.save(danhSachNguoiDiCung);
            }

            // Cập nhật tồn kho
            int soLuongGioHang = ctg1h.getSoNguoi();
            int soLuongHienTai = bienthetour.getSoLuongCon();
            bienthetour.setSoLuongCon(soLuongHienTai - soLuongGioHang);
            bienthetourRepository.save(bienthetour);

            // Xóa các mục giỏ hàng đã xử lý
            gioHangDanhSachNguoiDiCungRepository.deleteAll(listGhdsndc);
            chiTietGioHangRepository.delete(ctg1h);

            // Gửi email hóa đơn
            CompletableFuture.runAsync(() -> {
                try {
                    mailerService.sendInvoiceEmail(savedHoaDon.getId());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            // Trả về thông báo thành công và chuyển hướng về frontend
            return ResponseEntity.status(HttpStatus.FOUND)
			.header("Location", "http://localhost:3000/chi-tiet-hoa-don?paymentId=" + hoadon.getId())
                    .body("Payment processed and redirected to frontend...");
        } else {
            // Nếu thanh toán bị hủy, không lưu hóa đơn và trả về frontend
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "http://localhost:3000")
                    .body("Payment was canceled. Redirecting to frontend...");
        }

    } catch (Exception e) {
        // Xử lý lỗi
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
    }
}


	@GetMapping("/{id}")
	public ResponseEntity<List<ChiTietHoaDonsDTO>> getChiTietHoaDonById(@PathVariable("id") Integer idHoaDon) {
		List<ChiTietHoaDonsDTO> chiTietHoaDonList = hoaDonService.getChiTietHoaDonById(idHoaDon);
		if (chiTietHoaDonList.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(chiTietHoaDonList);
	}
}
