package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.DTO.ChiTietGioHangRequestDTO;
import com.example.DTO.GioHangDTO;
import com.example.Entity.BienTheTour;
import com.example.Entity.ChiTietGioHang;
import com.example.Entity.MediaTour;
import com.example.Entity.NguoiDung;
import com.example.Repository.BienTheTourRepository;
import com.example.Repository.ChiTietGioHangRepository;
import com.example.Repository.GioHangDanhSachNguoiDiCungRepository;
import com.example.Repository.NguoiDungRepository;

@Service
public class ChiTietGioHangService {

	@Autowired
	private ChiTietGioHangRepository chiTietGioHangRepository;
	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Autowired
	private BienTheTourRepository bienTheTourRepository;
	@Autowired
	private GioHangDanhSachNguoiDiCungRepository gioHangDanhSachNguoiDiCungRepository;

	// GET phương thức Chi tiết giỏ hàng
	public List<ChiTietGioHang> getAllChiTietGioHang() {
		return chiTietGioHangRepository.findAll();
	}

	// public List<Integer> getAllNguoiDungIds() {
	// return chiTietGioHangRepository.findAllNguoiDungIds();
	// }
	// Phương thức thêm mới chi tiết giỏ hàng
	public ChiTietGioHang saveChiTietGioHang(ChiTietGioHangRequestDTO dto) {
		ChiTietGioHang chiTietGioHang = new ChiTietGioHang();

		NguoiDung nguoiDung = nguoiDungRepository.findById(dto.getIdNguoiDung())
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		BienTheTour bienTheTour = bienTheTourRepository.findById(dto.getIdBienTheTour())
				.orElseThrow(() -> new IllegalArgumentException("Tour variant not found"));

		chiTietGioHang.setNguoiDung(nguoiDung);
		chiTietGioHang.setBienTheTour(bienTheTour);
		chiTietGioHang.setTongTien(dto.getTongTien());
		chiTietGioHang.setMoTa(dto.getMoTa());
		chiTietGioHang.setSoNguoi(dto.getSoNguoi());

		return chiTietGioHangRepository.save(chiTietGioHang);
	}

	public List<ChiTietGioHang> getChiTietGiohangByBienTheTourId(Integer idBienTheTour) {
		return chiTietGioHangRepository.findByChiTietGioHangId(idBienTheTour);
	}

	public boolean checkIfExists(int idNguoiDung, int idBienTheTour) {
		return chiTietGioHangRepository.existsByIdNguoiDungAndIdBienTheTour(idNguoiDung, idBienTheTour);
	}

	public Optional<ChiTietGioHang> findById(Integer id) {
		return chiTietGioHangRepository.findById(id);
	}

	// Phương thức update số người sao khi thêm giỏ hàng danh sách người đi cùng
	public void updateSoNguoi(Integer chiTietGioHangId) {
		Optional<ChiTietGioHang> optionalChiTietGioHang = chiTietGioHangRepository.findById(chiTietGioHangId);
		if (optionalChiTietGioHang.isPresent()) {
			ChiTietGioHang chiTietGioHang = optionalChiTietGioHang.get();
			int newSoNguoi = gioHangDanhSachNguoiDiCungRepository.countByChiTietGioHangId(chiTietGioHangId); // Assuming
																												// you
																												// have
																												// this
																												// method
			chiTietGioHang.setSoNguoi(newSoNguoi);
			chiTietGioHangRepository.save(chiTietGioHang);
		}
	}

	// -----------------------------------------
	// Phương thức lấy chi tiết giỏ hàng theo người dùng
	public List<GioHangDTO> getCartDetailsByUserId(Integer idNguoiDung) {
		// Gọi repository để lấy dữ liệu
		return chiTietGioHangRepository.findCartDetailsByUserId(idNguoiDung);
	}

	public ResponseEntity<String> deleteChiTietGioHang(Integer id) {
		System.out.println("id: "+ id);
		// Kiểm tra nếu giỏ hàng tồn tại
		Optional<ChiTietGioHang> chiTietGioHangOptional = chiTietGioHangRepository.findById(id);
		System.out.println("id:s "+ chiTietGioHangOptional);

		if (chiTietGioHangOptional.isPresent()) {
			// Xóa giỏ hàng
			chiTietGioHangRepository.deleteById(id);
			// Trả về thông báo thành công
			return ResponseEntity.ok("Xóa giỏ hàng thành công!");
		} else {
			// Trả về thông báo lỗi nếu không tìm thấy giỏ hàng
			throw new IllegalArgumentException("Giỏ hàng không tồn tại!");
		}
	}

}
