package com.example.Controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.TourDetailsDTO;
import com.example.Entity.BienTheTour;
import com.example.Entity.Tour;
import com.example.Repository.BienTheTourRepository;
import com.example.projection.TourDetailsProjection;
import com.example.service.TourService;

@RestController
@RequestMapping("/api/tours")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {

	@Autowired
	private TourService tourService;
	@Autowired
	private BienTheTourRepository bienTheTourRepository;

	@GetMapping
	public ResponseEntity<List<Tour>> getAllTour() {
		List<Tour> tours = tourService.getAllTours();
		return ResponseEntity.ok(tours);
	}

	// API POST: Thêm tour mới
	@PostMapping("/them")
	public ResponseEntity<Tour> addTour(@RequestBody Tour tour) {
		Tour savedTour = tourService.addTour(tour);
		return ResponseEntity.ok(savedTour);
	}

	// API GET: Lấy thông tin chi tiết tour theo ID
	@GetMapping("/{id}")
	public ResponseEntity<Tour> getTourById(@PathVariable Integer id) {
		Optional<Tour> tour = tourService.getTourById(id);
		if (tour.isPresent()) {
			return ResponseEntity.ok(tour.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// API PUT: Cập nhật tour theo ID
	@PutMapping("/update/{id}")
	public ResponseEntity<Tour> updateTour(@PathVariable("id") Integer id, @RequestBody Tour updatedTour) {
		Tour tour = tourService.updateTour(id, updatedTour);
		if (tour != null) {
			return ResponseEntity.ok(tour);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/update-status/{id}")
	public ResponseEntity<Tour> updateTourStatus(@PathVariable Integer id, @RequestBody Tour tour) {
	    // Chỉ truyền giá trị trangThai thay vì toàn bộ đối tượng Tour
	    Tour updatedTour = tourService.updateStatus(id, tour.isTrangThai());
	    if (updatedTour != null) {
	        return ResponseEntity.ok(updatedTour);
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	// API DELETE: xóa tour theo ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTour(@PathVariable Integer id) {
		try {
tourService.deleteTour(id);
			return new ResponseEntity<>("Người dùng đã được xóa thành công", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// GET thông tin trang sản phẩm
	@GetMapping("/info")
	public List<TourDetailsDTO> getAllTourInfo() {
		return tourService.getAllTourInfo();
	}

	// Endpoint to get all BienTheTour by Tour ID
	@GetMapping("/chitiet/{idTour}")
	public List<BienTheTour> getBienTheTourByTourId(@PathVariable Integer idTour) {
		return bienTheTourRepository.findByTourId(idTour);
	}

    @GetMapping("/byDanhMuc/{id}")
    public List<Object[]> getToursByDanhMuc(@PathVariable("id") Integer idDanhMucTour) {
        return tourService.getToursByDanhMuc(idDanhMucTour);
    }   
    @GetMapping("/search")
    public List<TourDetailsDTO> searchTours(@RequestParam("text") String tenTour) {
        return tourService.searchToursByName(tenTour);
    }
    @GetMapping("/danhmuctour/{tenDanhMuc}")
    public List<Tour> getToursEndDanhMucTour(@PathVariable("tenDanhMuc") String idDanhMucTour){
    	return tourService.getAllToursEndDanhMucTour(idDanhMucTour);
    }
    
}