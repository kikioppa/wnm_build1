package com.example.dodgema.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dodgema.model.Userr;

@Repository
public interface UserrRepository extends JpaRepository<Userr, Long>{

}