package com.example.dodgema.controller;



import com.example.dodgema.model.Spirit;
import com.example.dodgema.repository.SpiritRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.example.dodgema.service.SpiritService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class SpiritController {
    private final SpiritService spiritService;
    private final SpiritRepository spiritRepository;



    @Autowired
    public SpiritController(SpiritService spiritService, SpiritRepository spiritRepository) {
        this.spiritService = spiritService;
        this.spiritRepository = spiritRepository;
    }



    @GetMapping("/add_spirit")
    public String addSpiritForm(Spirit spirit,Model model) {

        model.addAttribute("spirit",new Spirit());
        return "add-spirit";
    }


    @Value("${file.path}")
    private String fileRealPath;

    @PostMapping("/add")
    @ResponseBody
    public String addSpirit(@Valid @ModelAttribute("form") MultipartFile files, Spirit spirit, Model model) throws IOException {


        try{
            String baseDir = "C:\\Users\\irlink\\Documents\\uploadFiles";
            String filePath = baseDir + "\\" + files.getOriginalFilename();
            files.transferTo((new File(filePath)));
            //spring security

            spirit.setSpiritImg(filePath);
        }catch(Exception e){
            e.printStackTrace();
        }

            spirit.setDate(LocalDateTime.now());
            spirit.setId(spirit.getId());
            spirit.setAbv(spirit.getAbv());


            System.out.println("패쓰"+spirit.getSpiritImg().getClass().getName());


            spiritService.saveItem(spirit);

        return "redirect:/";
    }

    @GetMapping("/spirit_view/{id}")
    public String showSpirit(@PathVariable("id") long id, Model model) {

        Spirit spirit = spiritService.findOne(id);

       /* SpiritForm spiritForm = new SpiritForm();

        spiritForm.setSpiritName(spirit.getSpiritName());
        spiritForm.setSpiritImageAddress(spirit.getSpiritImg());


            System.out.println("패쓰"+spirit.getSpiritImg());
            System.out.println("패쓰"+spiritForm.getSpiritImageAddress());


        model.addAttribute("spiritForm", spiritForm);*/
        return "spirit-view";
    }


}
