package com.example.dodgema.controller;



import com.example.dodgema.model.Price;
import com.example.dodgema.model.Spirit;
import com.example.dodgema.repository.SpiritRepository;
import com.example.dodgema.service.PriceService;
import com.example.dodgema.service.SpiritService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import javax.validation.Valid;
import java.io.File;
import java.time.LocalDateTime;

@Controller
public class SpiritController {
    private final SpiritService spiritService;
    private final PriceService priceService;
    private final SpiritRepository spiritRepository;
    public SpiritController(SpiritService spiritService, PriceService priceService, SpiritRepository spiritRepository) {
        this.spiritService = spiritService;
        this.priceService = priceService;
        this.spiritRepository = spiritRepository;
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
        price.setSpiritCode(price.getSpiritCode());



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
            String filePath = baseDir + "/" + savedName;

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

            spirit.setWiney(spirit.getWiney());
            spirit.setFloral(spirit.getFloral());
            spirit.setFruity(spirit.getFruity());
            spirit.setCereal(spirit.getCereal());
            spirit.setFeinty(spirit.getFeinty());
            spirit.setWoody(spirit.getWoody());
            spirit.setPeaty(spirit.getPeaty());
            spirit.setSulphury(spirit.getSulphury());

            spirit.setRegion(spirit.getRegion());
            spirit.setRare(spirit.getRare());


            spirit.setType(spirit.getType());
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
        model.addAttribute("price",priceService.findTo(spiritService.findById(id).getSpiritCode()));
        return "spirit-view";
    }


    @GetMapping("/delete_spirit/{id}")
    public String deleteSpirit(@PathVariable("id") long id, Model model) {

        spiritService.deleteSpiritById(id);
        //model.addAttribute("spirit", spiritService.findById(id));
        //model.addAttribute("price",priceService.findTo(spiritService.findById(id).getSpiritCode()));
        return "redirect:/";
    }


//사진 data 떄문에 업데이트 기능 추후 수정
   /* @GetMapping("/spirit_edit/{id}")
    public String editSpirit(@PathVariable("id") long id, Model model) {

        model.addAttribute("spirit", spiritService.findById(id));
        model.addAttribute("price",priceService.findTo(spiritService.findById(id).getSpiritCode()));
        return "spirit-update";
    }*/

 /*   @PostMapping("/spirit_update/{id}")
    public String updateSpirit(@PathVariable("id") long id, Model model,Spirit spirit) {

        spiritService.saveSpirit(spirit);
        model.addAttribute("spirit", spiritService.findById(id));
        return "spirit-list";
    }*/


    @RequestMapping("/price_data/{spiritCode}")
    @ResponseBody
    public List<Price> price_data(@PathVariable("spiritCode") Long spiritCode) {

        return priceService.findBySpiritCode(spiritCode);
    }

    @GetMapping("/taste_data/{id}")
    @ResponseBody
    public Map showSpirit(@PathVariable("id") long id) {
        Spirit spirit = spiritService.findById(id);
        Map result = new HashMap<String,Object>();
        result.put("cereal",spirit.getCereal());
        result.put("floral",spirit.getFloral());
        result.put("winey",spirit.getWiney());
        result.put("woody",spirit.getWoody());
        result.put("feinty",spirit.getFeinty());
        result.put("peaty",spirit.getPeaty());
        result.put("fruity",spirit.getFruity());
        result.put("sulphury",spirit.getSulphury());
        return result;
    }
/*
    @RequestMapping("/flavor_data")
    @ResponseBody
    public String flavor_data(long id) {
        return spiritService.findById(id);
    }
*/

}
