package com.example.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entity.BaiViet;

public interface BaiVietRepository extends JpaRepository<BaiViet, Integer> {
    // Phương thức tự động được tạo bởi Spring Data JPA để tìm theo ID
//	Optional<BaiViet> findById(Integer id);  // Phương thức này sẽ trả về Optional<BaiViet>
//    boolean existsById(Integer id); 
    List<BaiViet> findAllByOrderByIdDesc();
}
