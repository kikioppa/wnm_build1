package com.example.dodgema.model;

import lombok.Getter;
import lombok.Setter;
import com.example.dodgema.model.Spirit;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String title;
    private String memo;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Spirit spirit;


    @OneToMany(mappedBy = "review")
    private List<Comment> comments = new ArrayList<>();

}
