package com.example.dodgema.service;

import com.example.dodgema.model.Price;
import com.example.dodgema.model.Spirit;
import com.example.dodgema.repository.PriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Transactional
    public Price savePrice(Price price){
        return priceRepository.save(price);
    }

    public List<Price> AllPrice(){
        return priceRepository.findAll();
    }

    public Price findById(Long id){
        return priceRepository.findById(id).get();
    }

    public List<Price> findByName(String name){
        return priceRepository.findByName(name);
    }
}
