package com.example.dodgema.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static javax.persistence.FetchType.*;

@Entity
@SequenceGenerator(
        name="SPIRIT_SEQ_GEN",
        sequenceName = "SPIRIT_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
@Table(name = "spirit")
public class Spirit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SPIRIT_SEQ_GEN")
    private long id;

    @Column(name = "spirit_name")
    private String spiritName;
    @Column(name = "spirit_title")
    private String spiritTitle;
    @Column(name = "spirit_content")
    private  String spiritContent;
    @Column(name = "spirit_code")
    private Long spiritCode;
    @OneToMany(fetch = EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="spirit_code")
    private Collection<Price> price;
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
    @Column(name = "fruity")
    private Integer fruity;
    @Column(name = "floral")
    private Integer floral;
    @Column(name = "peaty")
    private Integer peaty;
    @Column(name = "cereal")
    private Integer cereal;
    @Column(name = "winey")
    private Integer winey;
    @Column(name = "feinty")
    private Integer feinty;
    @Column(name = "sulphury")
    private Integer sulphury;
    @Column(name = "woody")
    private Integer woody;

    @ManyToMany(mappedBy = "items", fetch = LAZY)
    private List<Category> category = new ArrayList<>();


}