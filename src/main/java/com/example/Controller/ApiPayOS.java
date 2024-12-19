package com.example.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import jakarta.servlet.http.HttpServletResponse;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api/v1/user")
public class ApiPayOS {
    private final PayOS payOS;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private MailerService mailerService;
    @Autowired
    private NguoiDungRepository nn;

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

    public ApiPayOS(PayOS payOS) {
        super();
        this.payOS = payOS;
    }

    Integer idNguoiDung;
    Integer id;
    HoaDonDTO hoaDonDTO;

    @RequestMapping(value = "/success")
    public void Success(HttpServletResponse response) throws IOException {
        ChiTietGioHang ctg1h = chiTietGioHangRepository.findById(id).get();
        NguoiDung nguoiDung = nn.findById(idNguoiDung).get();// 2
        HoaDon hoadon = new HoaDon();
        hoadon.setNguoiDung(nguoiDung);
        hoadon.setTongTien(hoaDonDTO.getTongTien()); // Example total price
        hoadon.setNgayThanhToan(new Date());
        hoadon.setPhuongThucThanhToan(true); // Example payment method (true for online)
        hoadon.setTrangThai(true); // Assuming true for a completed transaction
        HoaDon savedHoaDon = hoaDonRepository.save(hoadon); // Saving HoaDon
        BienTheTour bienthetour = bienthetourRepository.findById(hoaDonDTO.getIdBienTheTour()).orElse(null);

        ChiTietHoaDon chitiethoadon = new ChiTietHoaDon();
        chitiethoadon.setHoaDon(savedHoaDon); // Associate with the saved HoaDon
        chitiethoadon.setBienTheTour(bienthetour); // Set BienTheTour object
        chitiethoadon.setNgayDat(savedHoaDon.getNgayThanhToan());
        chitiethoadon.setTrangThai(true);
        chitiethoadon.setThanhTien(savedHoaDon.getTongTien());
        chitiethoadon.setGiaNguoiLon(bienthetour.getGiaNguoiLon());
        chitiethoadon.setGiaTreEm(bienthetour.getGiaTreEm());
        chitiethoadon.setMoTa(bienthetour.getMaTour());
        chiTietHoaDonRepository.save(chitiethoadon); //

        // Lấy danh sách người đi cùng từ ChiTietGioHang
        List<GioHangDanhSachNguoiDiCung> listGhdsndc = gioHangDanhSachNguoiDiCungRepository.findByChiTietGioHang(ctg1h);
        // System.out.println("Số lượng người đi cùng: " + listGhdsndc.size());

        // Lưu thông tin vào DanhSachNguoiDiCung
        for (GioHangDanhSachNguoiDiCung gioHangDanhSachNguoiDiCung : listGhdsndc) {

            DanhSachNguoiDiCung danhSachNguoiDiCung = new DanhSachNguoiDiCung();
            danhSachNguoiDiCung.setChiTietHoaDon(chitiethoadon); // Liên kết với ChiTietHoaDon đã lưu
            danhSachNguoiDiCung.setHoTen(gioHangDanhSachNguoiDiCung.getHoTen());
            danhSachNguoiDiCung.setEmail(gioHangDanhSachNguoiDiCung.getEmail());
            danhSachNguoiDiCung.setSoDienThoai(gioHangDanhSachNguoiDiCung.getSoDienThoai());
            danhSachNguoiDiCung.setNamSinh(gioHangDanhSachNguoiDiCung.getNamSinh());
            danhSachNguoiDiCungRepository.save(danhSachNguoiDiCung); // Lưu thông tin người đi cùng
        }

        // Cập nhật số lượng còn lại của BienTheTour
        int soLuongGioHang = ctg1h.getSoNguoi(); // Số lượng từ giỏ hàng
        int soLuongHienTai = bienthetour.getSoLuongCon(); // Số lượng hiện tại của BienTheTour
        bienthetour.setSoLuongCon(soLuongHienTai - soLuongGioHang);
        bienthetourRepository.save(bienthetour); // Cập nhật lại BienTheTour với số lượng còn lại

        // Xóa danh sách người đi cùng khỏi giỏ hàng và xóa ChiTietGioHang sau khi lưu
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
        response.sendRedirect("http://localhost:3000");
    }

    @RequestMapping(value = "/cancel")
    public void Cancel(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:3000/gio-hang");
    }

    @PostMapping("/create-payment-link/{id}")
    public void checkout(@PathVariable("id") Integer ids, @RequestBody HoaDonDTO hoaDonDTOs,
            HttpServletResponse httpServletResponse) {
        try {
            idNguoiDung = hoaDonDTOs.getIdNguoiDung(); // Extract from DTO
            id = ids;
            hoaDonDTO = hoaDonDTOs;
            // Payment processing logic remains unchanged
            final String productName = "THANH TOAN ";
            final String description = "Thanh toán đơn hàng";
            final String returnUrl = "http://localhost:8080/api/v1/user/success";
            final String cancelUrl = "http://localhost:8080/api/v1/user/cancel";

            Integer totalOrderNew = (int) hoaDonDTOs.getTongTien();
            String total = String.valueOf(totalOrderNew).replace(".0", "");
            final int price = Integer.parseInt(total);

            // Generate order code
            String currentTimeString = String.valueOf(new Date().getTime());
            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));
            ItemData item = ItemData.builder().name(productName).quantity(1).price(price).build();
            PaymentData paymentData = PaymentData.builder().orderCode(orderCode).amount(price).description(description)
                    .returnUrl(returnUrl).cancelUrl(cancelUrl).item(item).build();

            // Create payment link
            CheckoutResponseData data = payOS.createPaymentLink(paymentData);
            String checkoutUrl = data.getCheckoutUrl();

            // Send checkout URL to frontend
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write("{\"checkoutUrl\":\"" + checkoutUrl + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}