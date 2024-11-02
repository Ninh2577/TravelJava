package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.DatTour;
import com.example.service.DatTourService;

@RestController
@RequestMapping("/api/dattour")
@CrossOrigin(origins = "http://localhost/3000")
public class DatTourController {
	@Autowired 
	private DatTourService datTourService;
	
	@GetMapping
	public ResponseEntity<List<DatTour>> getAllDatTour(){
		List<DatTour> datTours = datTourService.getAllDatTour();
		return ResponseEntity.ok(datTours);
	} 
}	