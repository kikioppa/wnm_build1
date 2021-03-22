package com.example.dodgema.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dodgema.model.Userr;
import com.example.dodgema.repository.UserrRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class UserrController {

    @Autowired
    private UserrRepository userrRepository;

    @GetMapping("users")
    public List < Userr > getUsers() {
        return this.userrRepository.findAll();
    }
}