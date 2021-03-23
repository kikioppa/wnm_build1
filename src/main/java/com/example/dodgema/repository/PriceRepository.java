package com.example.dodgema.repository;

import com.example.dodgema.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {


    List<Price> findBySpiritCode(Long spiritCode);
}