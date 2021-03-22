package com.example.dodgema.controller;


import com.example.dodgema.model.Mart;
import com.example.dodgema.repository.MartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/martList/")
public class MartController {

    private final MartRepository martRepository;

    @Autowired
    public MartController(MartRepository martRepository) {
        this.martRepository = martRepository;
    }

    @GetMapping("addMart")
    public String showAddForm(Mart mart) {
        return "add-mart";
    }


    @GetMapping("list")
    public String showListForm(Model model) {
        model.addAttribute("martList", martRepository.findAll());
        return "tdex";
    }


    @PostMapping("add")
    public String addMart(@Valid Mart mart, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-mart";
        }

        martRepository.save(mart);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Mart mart = martRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid mart Id:" + id));
        model.addAttribute("mart", mart);
        return "update-mart";
    }

    @PostMapping("update/{id}")
    public String updateMart(@PathVariable("id") long id, @Valid Mart mart, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            mart.setId(id);
            return "update-mart";
        }

        martRepository.save(mart);
        model.addAttribute("martList", martRepository.findAll());
        return "tdex";
    }

    @PostMapping("switchMartStatus/{id}")
    public String updateStatus(@PathVariable("id") long id, @Valid Mart mart, BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            mart.setId(id);
            return "update-mart";
        }

        martRepository.save(mart);
        model.addAttribute("martList", martRepository.findAll());
        return "tdex";
    }


    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Mart mart = martRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        martRepository.delete(mart);
        model.addAttribute("martList", martRepository.findAll());
        return "tdex";
    }
}