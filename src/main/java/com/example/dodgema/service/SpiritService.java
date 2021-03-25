package com.example.dodgema.service;


import com.example.dodgema.model.Spirit;
import com.example.dodgema.repository.SpiritRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpiritService {

    private final SpiritRepository spiritRepository;

    public SpiritService(SpiritRepository spiritRepository) {
        this.spiritRepository = spiritRepository;
    }

    @Transactional
    public Spirit saveSpirit(Spirit spirit){
        return spiritRepository.save(spirit);
    }

    public List<Spirit> AllSpirit(){
        return spiritRepository.findAll();
    }

    public Spirit findById(Long id){
        return spiritRepository.findById(id).get();
    }

/*
    List<Spirit> AllSpirit();
    Spirit getSpiritById(long id);
    void deleteSpiritById(long id);
    Page<Spirit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);*/
}