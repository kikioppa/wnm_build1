package com.example.dodgema.controller;

import com.example.dodgema.model.Mart;
import com.example.dodgema.model.Spirit;
import com.example.dodgema.repository.MartRepository;
import com.example.dodgema.repository.SpiritRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;


@Controller
public class MainController {
    private final MartRepository martRepository;
    private final SpiritRepository spiritRepository;
    @Autowired
    public MainController(MartRepository martRepository,SpiritRepository spiritRepository) {

        this.martRepository = martRepository;
        this.spiritRepository = spiritRepository;
    }


    @RequestMapping("/test")
    @ResponseBody
    public String indexx(Model model){

        return "/fragments/header";
    }

    @RequestMapping("/data")
    @ResponseBody
    public List<Mart> data() {
        return martRepository.findAll();
    }

    @RequestMapping("/data2")
    @ResponseBody
    public List<Mart> data2() {
        return martRepository.findAll();
    }



    @GetMapping("/spirits")
    public String sprts(Model model) {

        model.addAttribute("martList", martRepository.findTop5ByOrderByIdDesc());
        model.addAttribute("data",spiritRepository.findAll());
        return "spirits";
    }


    @GetMapping("/spirit_view")
    public String showSpirsit(){

        return "spirit-view";
    }


}