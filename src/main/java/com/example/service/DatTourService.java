package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.DatTour;
import com.example.Repository.ChiTietHoaDonRepository;

@Service
public class DatTourService {

	@Autowired
	private ChiTietHoaDonRepository datTourRepository;

	// GET phương thức Đặt tour
	public List<DatTour> getAllDatTour() {
		return datTourRepository.findAll();
	}
}
