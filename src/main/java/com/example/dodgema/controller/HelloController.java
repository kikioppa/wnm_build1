package com.example.dodgema.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController{


    @GetMapping("/ttest")
    public String showUpdateForm(Model model) {

        return "test";
    }
}
