package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entity.MediaTour;
import com.example.Repository.MediaTourRepository;
import jakarta.transaction.Transactional;

@Service
public class MediaTourService {

	@Autowired
	private MediaTourRepository mediaTourRepository;

	// Phương thức GET hết thông tin MediaTour
	public List<MediaTour> getAllMediaTour() {
		return mediaTourRepository.findAll();
	}

	// Phương thức thêm MediaTour
	public MediaTour addMediaTour(MediaTour mediaTour) {
		return mediaTourRepository.save(mediaTour);
	}

	// Phương thức cập nhật MediaTour
	@Transactional
	public MediaTour updatedMediaTour(Integer id, MediaTour updatedMediaTour) {
		Optional<MediaTour> existingMediaTour = mediaTourRepository.findById(id);
		if (existingMediaTour.isPresent()) {
			MediaTour mediaTour = existingMediaTour.get();
			mediaTour.setTour(updatedMediaTour.getTour());
			mediaTour.setHinhAnh(updatedMediaTour.getHinhAnh());
			mediaTour.setVideo(updatedMediaTour.getVideo());
			return mediaTourRepository.save(mediaTour);
		} else {
			return null;
		}
	}

	// Phương thức xóa MediaTour
	@Transactional
	public void deleteMediaTour(Integer id) {
		Optional<MediaTour> optionalMediaTour = mediaTourRepository.findById(id);
		if (optionalMediaTour.isPresent()) {
			mediaTourRepository.deleteById(id);
		} else {
			throw new RuntimeException("MediaTour không tồn tại với ID:" + id);
		}
	}

	public List<MediaTour> getMediaByTourId(Integer tourId) {
		return mediaTourRepository.findByTour_Id(tourId);
	}
	 public List<MediaTour> getMediaTourByTourId(Integer idTour) {
	        return mediaTourRepository.findByMediaTourId(idTour);
	    }
}
