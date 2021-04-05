package com.example.dodgema.controller;

import com.example.dodgema.config.security.PrincipalDetail;
import com.example.dodgema.model.Spirit;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import com.example.dodgema.service.*;
import com.example.dodgema.repository.SpiritRepository;


@Controller
@RequiredArgsConstructor
public class HomeController {
    //private final 멤버서비스 들어가야댐

    private final SpiritRepository spiritRepository;
    private final int maxItem = 6;
    private final int viewPage = 5;

    //@AuthenticationPrincipal PrincipalDetail principal
    @GetMapping(value = "/")
    public String home(HttpSession session, Model model) {
        List<Spirit> spiritIdx = spiritRepository.findAll();


        model.addAttribute("spiritIdx", spiritIdx);

        System.out.println("1번");
        return "index";
    }

/*
    @GetMapping(value = "/{sort}/{page}")
    public String homePage(@PathVariable("page") int page, @PathVariable("sort") String sort, HttpSession session, Model model) {
        List<?> spirits = new ArrayList<>();

        try {
            spirits = pagingService.getBoardPage(spiritService.findBySort(sort), page, this.maxItem, this.viewPage, model);
            model.addAttribute("spirits", spirits);
            model.addAttribute("sort", sort);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("2번");

        return "index";
    }

*/




}
