package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.VaiTro;
import com.example.Repository.VaiTroRepository;

@Service
public class VaiTroService {

		@Autowired
	private VaiTroRepository vaiTroRepository;

	// Phương thức GET hết thông tin người dùng
	public List<VaiTro> getAllVaiTros() {
		return vaiTroRepository.findAll();
	}

}
