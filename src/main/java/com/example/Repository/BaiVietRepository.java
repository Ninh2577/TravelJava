package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entity.BaiViet;

public interface BaiVietRepository extends JpaRepository<BaiViet, Integer> {
    // Phương thức tự động được tạo bởi Spring Data JPA để tìm theo ID
}
