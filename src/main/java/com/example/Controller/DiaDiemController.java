package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.DiaDiem;
import com.example.service.DiaDiemService;

@RestController
@RequestMapping("/api/diadiem")
@CrossOrigin(origins = "http://localhost:3000")
public class DiaDiemController {
	@Autowired
	private DiaDiemService diaDiemService;
	
	//Phương thức GET thông tin địa điểm
	@GetMapping
	public ResponseEntity<List<DiaDiem>> getAllDiaDiem(){
		List<DiaDiem> diaDiem = diaDiemService.getAllDiaDiem();
		return ResponseEntity.ok(diaDiem);
	}
	//API POST : Thêm địa điểm mới
	@PostMapping("/them")
	public ResponseEntity<DiaDiem> addDiaDiem (@RequestBody DiaDiem diaDiem){
		DiaDiem savedDiaDiem = diaDiemService.addDiaDiem(diaDiem);
		return ResponseEntity.ok(savedDiaDiem);
	}
	//API PUT : Cập nhật Địa điểm theo id
	@PutMapping("/update/{id}")
	public ResponseEntity<DiaDiem> updateDiaDiem(@PathVariable("id") Integer id, @RequestBody DiaDiem updateDiaDiem){
		DiaDiem diaDiem = diaDiemService.updateDiaDiem(id, updateDiaDiem);
		if(diaDiem != null) {
			return ResponseEntity.ok(diaDiem);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	//API DELETE : Xóa Địa điểm theo id 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDiaDiem(@PathVariable Integer id){
		try {
			diaDiemService.deleteDiaDiem(id);
			return new ResponseEntity<>("Địa điểm đã được xóa thành công", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}
