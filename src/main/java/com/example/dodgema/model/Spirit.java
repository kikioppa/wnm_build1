package com.example.dodgema.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "spirit")
public class Spirit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "spirit_name")
    private String spiritName;
    private String spiritImg;
    @Column(name = "spirit_score")
    private int spiritScore;
    @Column(name = "spirit_rating")
    private int spiritRating;
    @Column(name = "rare")
    private int rare;
    @Column(name = "Local_Date_Time")
    private LocalDateTime date;
    @Column(name = "cask_type")
    private String caskType;
    @Column(name = "abv")
    private String abv;
    @Column(name = "type")
    private String type;
    @Column(name = "region")
    private String region;

    @Column(name = "smokey")
    private int smokey;
    @Column(name = "peat")
    private int peat;
    @Column(name = "herbal")
    private int herbal;
    @Column(name = "oily")
    private int oily;
    @Column(name = "full")
    private int full;
    @Column(name = "rich")
    private int rich;
    @Column(name = "sweat")
    private int sweat;
    @Column(name = "briny")
    private int briny;
    @Column(name = "salty")
    private int salty;
    @Column(name = "vanilla")
    private int vanilla;
    @Column(name = "tart")
    private int tart;
    @Column(name = "fruity")
    private int fruity;
    @Column(name = "floral")
    private int floral;

    @ManyToMany(mappedBy = "items", fetch = LAZY)
    private List<Category> category = new ArrayList<>();


}