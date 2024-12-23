package com.example.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.NguoiDung;
import com.example.Entity.Tour;
import com.example.Entity.YeuThich;

public interface YeuThichRepository extends JpaRepository<YeuThich, Integer> {
    Optional<YeuThich> findByNguoiDungAndTour(NguoiDung nguoiDung, Tour tour);

    @Query("SELECT y.tour FROM YeuThich y WHERE y.nguoiDung.id = :userId AND y.thich = true")
    List<Tour> findLikedToursByUserId(@Param("userId") Integer userId);

    // @Query("DELETE FROM YeuThich y WHERE y.nguoiDung.id = :userId")
    // void deleteAllByUserId(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE YeuThich y SET y.thich = false WHERE y.nguoiDung.id = :userId")
    void deleteAllByUserId(@Param("userId") Integer userId);
}

