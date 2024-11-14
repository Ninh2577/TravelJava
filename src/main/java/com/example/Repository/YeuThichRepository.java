package com.example.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entity.NguoiDung;
import com.example.Entity.Tour;
import com.example.Entity.YeuThich;

public interface YeuThichRepository extends JpaRepository<YeuThich, Integer> {
    Optional<YeuThich> findByNguoiDungAndTour(NguoiDung nguoiDung, Tour tour);
}
