package com.example.dodgema.controller;



import com.example.dodgema.model.Mart;
import com.example.dodgema.model.Price;
import com.example.dodgema.model.Spirit;
import com.example.dodgema.service.PriceService;
import com.example.dodgema.service.SpiritService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import java.io.File;
import java.time.LocalDateTime;

@Controller
public class SpiritController {
    private final SpiritService spiritService;
    private final PriceService priceService;

    public SpiritController(SpiritService spiritService, PriceService priceService) {
        this.spiritService = spiritService;
        this.priceService = priceService;
    }


    @GetMapping("/add_spirit")
    public String addSpiritForm(Spirit spirit,Model model) {

        model.addAttribute("spirit",spirit);
        return "add-spirit";
    }

    @GetMapping("/add_price")
    public String addPriceForm(Price price, Model model) {


        model.addAttribute("price",price);
        return "add-price";
    }

    @PostMapping("/add_price_add")
    public String addPrice(@ModelAttribute("price") Price price,Model model){

        price.setPrice(price.getPrice());
        price.setDate(price.getDate());




        model.addAttribute("price",price);


        priceService.savePrice(price);
        return "redirect:/";
    }

    @Value("${file.path}")
    private String fileRealPath;

    @PostMapping("/add")
    @ResponseBody
    public String addSpirit(@RequestParam("spiritImg") MultipartFile files,@Valid Spirit spirit,BindingResult result, Model model) {
/*       if (result.hasErrors()) {
            return "err";
        }*/

        try{
            String baseDir = fileRealPath;
            String savedName = UUID.randomUUID().toString() + "_" + files.getOriginalFilename();
            String filePath = baseDir + "\\" + savedName;

            files.transferTo((new File(filePath)));
            //spring security
            spirit.setDate(LocalDateTime.now());
            spirit.setId(spirit.getId());
            spirit.setAbv(spirit.getAbv());
            spirit.setSpiritName(spirit.getSpiritName());
            spirit.setSpiritImg(savedName);
            spirit.setSpiritScore(spirit.getSpiritScore());
            spirit.setCaskType(spirit.getCaskType());
            spirit.setSpiritRating(spirit.getSpiritRating());
            spirit.setVanilla(spirit.getVanilla());
            spirit.setTart(spirit.getTart());
            spirit.setSweat(spirit.getSweat());
            spirit.setSmokey(spirit.getSmokey());
            spirit.setSalty(spirit.getSalty());
            spirit.setRich(spirit.getRich());
            spirit.setPeat(spirit.getPeat());
            spirit.setRegion(spirit.getRegion());
            spirit.setRare(spirit.getRare());
            spirit.setOily(spirit.getOily());
            spirit.setFloral(spirit.getFloral());
            spirit.setFruity(spirit.getFruity());
            spirit.setFull(spirit.getFull());
            spirit.setBriny(spirit.getBriny());
            spirit.setType(spirit.getType());
            spirit.setHerbal(spirit.getHerbal());
        }catch(Exception e){
            e.printStackTrace();
        }

            System.out.println("서비스 실행");
            spiritService.saveSpirit(spirit);

        return "redirect:/";
    }



    @GetMapping("/spirit_view/{id}")
    public String showSpirit(@PathVariable("id") long id, Model model) {



        model.addAttribute("spirit", spiritService.findById(id));
        return "spirit-view";
    }


    @RequestMapping("/price_data/")
    @ResponseBody
    public List<Price> price_data(String name) {
        return priceService.findByName(name);
    }

/*
    @RequestMapping("/flavor_data")
    @ResponseBody
    public String flavor_data(long id) {
        return spiritService.findById(id);
    }
*/

}
