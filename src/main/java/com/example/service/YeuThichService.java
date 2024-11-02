package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.YeuThich;
import com.example.Repository.YeuThichRepository;

@Service
public class YeuThichService {

	@Autowired
	private YeuThichRepository yeuThichRepository;
	//GET Phương thức Yêu thích
	public List<YeuThich> getAllYeuThich(){
		return yeuThichRepository.findAll();
	}
}
