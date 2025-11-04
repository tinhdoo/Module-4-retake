package com.example.football.controller;

import com.example.football.entity.Player;
import com.example.football.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/")
    public String home() {
        return "redirect:/players";
    }

    @GetMapping("/players")
    public ModelAndView listPlayers() {
        ModelAndView mv = new ModelAndView("player/list");
        mv.addObject("players", playerService.getAll());
        return mv;
    }

    @GetMapping("/players/{code}")
    public String viewPlayer(@PathVariable("code") Integer code, Model model) {
        Optional<Player> playerOpt = playerService.findByCode(code);
        if (playerOpt.isPresent()) {
            model.addAttribute("player", playerOpt.get());
            return "player/detail";
        } else {
            model.addAttribute("error", "Không tìm thấy cầu thủ có mã " + code);
            return "error";
        }
    }


    @GetMapping("/players/delete/{code}")
    public String deletePlayer(@PathVariable("code") Integer code, Model model) {
        playerService.delete(code);
        return "redirect:/players";
    }

    @GetMapping("/players/add")
    public String addPlayer(Model model){
        model.addAttribute("player", new Player());
        return "player/add";
    }
    @PostMapping("/players/add")
    public String addPlayer(@ModelAttribute("player") Player player, RedirectAttributes redirectAttributes){
        playerService.save(player);
        redirectAttributes.addFlashAttribute("mess", "Thêm cầu thủ thành công");
        return "redirect:/players";
    }
}
