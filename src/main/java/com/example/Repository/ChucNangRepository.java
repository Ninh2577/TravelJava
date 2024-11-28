package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.ChucNang;

@Repository
public interface ChucNangRepository extends JpaRepository<ChucNang, Integer> {

}
