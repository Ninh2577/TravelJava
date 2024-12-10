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