package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.VaiTro;
import com.example.service.VaiTroService;

@RestController
@RequestMapping("/api/vaitro")
@CrossOrigin(origins = "http://localhost:3000")
public class VaiTroController {

	@Autowired
	private VaiTroService vaiTroService;

	@GetMapping
	public ResponseEntity<List<VaiTro>> getAllTour() {
		List<VaiTro> vaiTro = vaiTroService.getAllVaiTros();
		return ResponseEntity.ok(vaiTro);
	}
}
