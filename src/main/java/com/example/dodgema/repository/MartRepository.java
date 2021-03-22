package com.example.dodgema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dodgema.model.Mart;

import java.util.List;


@Repository
    public interface MartRepository extends JpaRepository <Mart, Long> {
    List<Mart> findByMartName(String martName);
    List<Mart> findByMartLocation(String martLocation);
    List<Mart> findTop5ByOrderByIdDesc();
    }

