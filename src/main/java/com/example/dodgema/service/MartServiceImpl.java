package com.example.dodgema.service;

import com.example.dodgema.model.Mart;
import com.example.dodgema.repository.MartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MartServiceImpl implements MartService {

    @Autowired
    private MartRepository martRepository;

    @Override
    public List<Mart> getAllMart() {
        return martRepository.findAll();
    }

    @Override
    public void saveMart(Mart mart) {
        this.martRepository.save(mart);
    }

    @Override
    public Mart getMartById(long id) {

        Optional<Mart> optional = martRepository.findById(id);
        Mart mart = null;
        if(optional.isPresent()){
            mart = optional.get();
        }else{
            throw new RuntimeException("Mart not found for id : :"+ id);
        }
        return mart;
    }

    @Override
    public void deleteMartById(long id) {
        this.martRepository.deleteById(id);
    }

    @Override
    public Page<Mart> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
        Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);

        return this.martRepository.findAll(pageable);
    }
}
