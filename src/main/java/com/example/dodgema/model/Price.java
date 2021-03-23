package com.example.dodgema.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.*;

@Entity
@SequenceGenerator(
        name="PRICE_SEQ_GEN",
        sequenceName = "PRICE_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRICE_SEQ_GEN")
    @Column(name = "price_id")
    private Long id;

    @Column(name="spirit_Name")
    private long spiritName;

    @Column(name = "price")
    private int price;
    @Column(name = "date")
    private Date date;

    private String memo;

}