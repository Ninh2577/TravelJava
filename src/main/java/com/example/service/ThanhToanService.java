package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entity.ThanhToan;
import com.example.Repository.ThanhToanRepository;

@Service
public class ThanhToanService {
	@Autowired
	private ThanhToanRepository thanhToanRepository;

	// Method to retrieve all ThanhToan records
	public List<ThanhToan> getAllThanhToan() {
		return thanhToanRepository.findAll();
	}

	public Optional<ThanhToan> getThanhToanById(Integer id) {
		return thanhToanRepository.findById(id);
	}

	public List<Object[]> getTourDatTourInfo(Integer thanhToanId) {
	    return thanhToanRepository.getTourDatTourInfoByThanhToanId(thanhToanId);
	}

}
