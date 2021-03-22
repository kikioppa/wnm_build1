package com.example.dodgema.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "mart")
public class Mart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "mart_name")
    private String martName;

    @NotBlank(message = "should be filled")
    @Column(name = "mart_location")
    private String martLocation;

    @Column(name = "mart_hours")
    private String martHours;

    @Column(name = "mart_phone")
    private long martPhone;


    public boolean martStatus;

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMartName() {
        return martName;
    }

    public void setMartName(String martName) {
        this.martName = martName;
    }

    public String getMartLocation() {
        return martLocation;
    }

    public void setMartLocation(String martLocation) {
        this.martLocation = martLocation;
    }

    public String getMartHours() {
        return martHours;
    }

    public void setMartHours(String martHours) {
        this.martHours = martHours;
    }

    public long getMartPhone() {
        return martPhone;
    }
    public void setMartPhone(long martPhone) { this.martPhone = martPhone; }

    public boolean isMartStatus() {
        return martStatus;
    }

    public void setMartStatus(boolean martStatus) {
        this.martStatus = martStatus;
    }

}
