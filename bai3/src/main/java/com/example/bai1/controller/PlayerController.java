package com.example.bai1.controller;

import com.example.bai1.entity.Player;
import com.example.bai1.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PlayerController {

    @Autowired
    private PlayerServiceImpl playerService;

    @GetMapping("/")
    public String home() {
        return "redirect:/players";
    }

    @GetMapping("/players")
    public ModelAndView listPlayers() {
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("players", playerService.getAll());
        return mv;
    }

    @GetMapping("/players/{code}")
    public ModelAndView viewPlayer(@PathVariable String code) {
        ModelAndView mv = new ModelAndView("detail");
        mv.addObject("player", playerService.findByCode(code));
        return mv;
    }


    @GetMapping("/players/delete/{code}")
    public String deletePlayer(@PathVariable String code) {
        playerService.delete(code);
        return "redirect:/players";
    }

    @GetMapping("/players/add")
    public String addPlayer(Model model){
        model.addAttribute("player", new Player());
        return "/add";
    }
    @PostMapping("/players/add")
    public String addPlayer(@ModelAttribute("player") Player player, RedirectAttributes redirectAttributes){
        playerService.save(player);
        redirectAttributes.addFlashAttribute("mess", "Thêm cầu thủ thành công");
        return "redirect:/players";
    }
}
