package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Hotels;

public interface HotelsRepository extends JpaRepository<Hotels, Integer>{

}
