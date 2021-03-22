package com.example.dodgema.repository;

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
@RequiredArgsConstructor
public class SpiritRepository {
    private final EntityManager em;

    public void save(Spirit spirit) {
                    em.merge(spirit);
    }

    public void delete(Spirit spirit) {
        em.remove(spirit);

    }

    public Spirit findOne(Long id) {
        return em.find(Spirit.class, id);
    }

    public List<Spirit> findAll() {
        return em.createQuery("select i from Spirit i order by i.date desc", Spirit.class)
                .getResultList();
    }


    /*
    public int findItemsCount() {
        return em.createQuery("select count(i) from Item i", Item.class)
                .getFirstResult();
    }
     */

    // 리뷰순 정렬
    public List<Spirit> findAllOrderByReview() {
        return em.createQuery("select i from Spirit i left join Review r on i.id =r.spirit.id group by i.id Order by count(r) desc, i.date desc", Spirit.class)
                .getResultList();
    }

/*
    // 1차 방법 : orderItem join
    public List<Spirit> findAllOrderBySale() {
        return em.createQuery("select i from Item i left join Cart c on i.id = c.item.id group by i.id order by sum(c.count) DESC, i.date desc", Item.class)
                .getResultList();
    }*/

   public List<Spirit> findByCategory(int select) {
        switch (select) {
            case 4:
                return em.createQuery("select b from Spirit b order by b.date desc", Spirit.class)
                        .getResultList();
            //case 5: return em.createQuery("select a from Album a order by a.date desc", Spirit.class).getResultList();
            //case 6: return em.createQuery("select m from Movie m order by m.date desc", Spirit.class).getResultList();
        }
        return null;
    }
}