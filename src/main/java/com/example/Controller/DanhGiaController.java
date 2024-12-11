package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.DanhGia;
import com.example.Entity.MediaTour;
import com.example.service.DanhGiaService;

@RestController
@RequestMapping("/api/danhgia")
@CrossOrigin(origins = "http://localhost/3000")
public class DanhGiaController {

  @Autowired
  private DanhGiaService danhGiaService;

  // GET Phương thức Đánh giá
  @GetMapping
  public ResponseEntity<List<DanhGia>> getAllDanhGia() {
    List<DanhGia> danhGias = danhGiaService.getAllDanhGia();
    return ResponseEntity.ok(danhGias);
  }

  @GetMapping("/{id}")
  public List<DanhGia> getDanhGiaByTourId(@PathVariable Integer id) {
    return danhGiaService.getDanhGiarByTourId(id);
  }

  @GetMapping("/danhgia/{tenTour}")
  public List<DanhGia> getTourAllDanhGia(@PathVariable String tenTour) {
    return danhGiaService.getAllTourByDanhGia(tenTour);
  }

  // Phương thức thêm đánh giá
  @PostMapping("/them")
  public ResponseEntity<DanhGia> addDanhGia(@RequestBody DanhGia danhGia) {
    DanhGia savedDanhGia = danhGiaService.addDanhGia(danhGia);
    return new ResponseEntity<>(savedDanhGia, HttpStatus.CREATED);
  }

  // // Phương thức cập nhật đánh giá
  // @PutMapping("/capnhat/{id}")
  // public ResponseEntity<DanhGia> updateDanhGia(@PathVariable Integer id,
  // @RequestBody DanhGia danhGiaDetails) {
  // Optional<DanhGia> updatedDanhGia = danhGiaService.updateDanhGia(id,
  // danhGiaDetails);
  // return updatedDanhGia.map(danhGia -> new ResponseEntity<>(danhGia,
  // HttpStatus.OK))
  // .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  // }
  //
  // // Phương thức xóa đánh giá
  // @DeleteMapping("/xoa/{id}")
  // public ResponseEntity<Void> deleteDanhGia(@PathVariable Integer id) {
  // danhGiaService.deleteDanhGia(id);
  // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  // }
}