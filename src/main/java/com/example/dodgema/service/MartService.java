package com.example.dodgema.service;

import com.example.dodgema.model.Mart;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MartService {
    List<Mart> getAllMart();
    void saveMart(Mart mart);
    Mart getMartById(long id);
    void deleteMartById(long id);
    Page<Mart> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}