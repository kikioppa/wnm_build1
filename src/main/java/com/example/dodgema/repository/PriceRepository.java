package com.example.dodgema.repository;

import com.example.dodgema.model.Price;
import com.example.dodgema.model.Spirit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findAll();

    List<Price> findBySpiritCode(Long spiritCode);
    List<Price> findTop5BySpiritCodeOrderByDateDesc(Long spiritCode);
    //List<Price> findBySpiritCode(Long spiritCode);
}