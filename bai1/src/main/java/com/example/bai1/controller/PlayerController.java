package com.example.bai1.controller;

import com.example.bai1.entity.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerController {
    @GetMapping("/player")
    public ModelAndView getPlayerDetails() {
        Player player = new Player("1", "Lionel Messi", "1987-06-24", "18 years", "Forward", "https://upload.wikimedia.org/wikipedia/commons/c/c1/Lionel_Messi_20180626.jpg");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("player", player);
        return modelAndView;

    }

    
}
