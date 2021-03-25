package com.example.dodgema.repository;

import com.example.dodgema.model.Mart;
import com.example.dodgema.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dodgema.model.Spirit;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface SpiritRepository extends JpaRepository <Spirit, Long> {
    List<Spirit> findAll();
}
