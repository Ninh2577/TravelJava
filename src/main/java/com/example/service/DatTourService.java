package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.DatTour;
import com.example.Repository.DatTourRepository;

@Service
public class DatTourService {
	  @Autowired
	    private DatTourRepository datTourRepository;

	    // Fetch all DatTour records
	    public List<DatTour> findAll() {
	        return datTourRepository.findAll();
	    }

		public Optional<DatTour> findById(Integer id) {
	        return datTourRepository.findById(id); // This interacts with the repository
	    }

		public Optional<DatTour> getDatTourById(Integer id) {
			return datTourRepository.findById(id);
		}
}
