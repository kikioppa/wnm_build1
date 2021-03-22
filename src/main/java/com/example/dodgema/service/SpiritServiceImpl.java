/*
package com.example.dodgema.service;

import com.example.dodgema.model.Spirit;
import com.example.dodgema.repository.SpiritRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpiritServiceImpl implements SpiritService {

    @Autowired
    private SpiritRepository spiritRepository;

    @Override
    public List<Spirit> getAllSpirit() { return spiritRepository.findAll(); }

    @Override
    public void saveSpirit(Spirit spirit) {
        this.spiritRepository.save(spirit);
    }

    @Override
    public Spirit getSpiritById(long id) {

        Optional<Spirit> optional = spiritRepository.findById(id);
        Spirit spirit = null;
        if(optional.isPresent()){
            spirit = optional.get();
        }else{
            throw new RuntimeException("Spirit not found for id : :"+ id);
        }
        return spirit;
    }

    @Override
    public void deleteSpiritById(long id) {
        this.spiritRepository.deleteById(id);
    }

    @Override
    public Page<Spirit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);

        return this.spiritRepository.findAll(pageable);
    }
}
*/
