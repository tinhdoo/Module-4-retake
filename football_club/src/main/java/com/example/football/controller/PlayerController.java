package com.example.football.controller;

import com.example.football.entity.Player;
import com.example.football.entity.Team;
import com.example.football.service.PlayerService;
import com.example.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public String home() {
        return "redirect:/players";
    }

    @GetMapping("/players")
    public String listPlayers(@RequestParam(defaultValue = "0") int page,
                              Model model) {
        int pageSize =6;
        Page<Player> playerPage = playerService.getAll(PageRequest.of(page, pageSize));

        model.addAttribute("players", playerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", playerPage.getTotalPages());
        return "player/list";
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
        model.addAttribute("teams", teamService.getAll());
        return "player/add";
    }
    @PostMapping("/players/add")
    public String addPlayer(@ModelAttribute("player") Player player, RedirectAttributes redirectAttributes){
        playerService.save(player);
        redirectAttributes.addFlashAttribute("mess", "Thêm cầu thủ thành công");
        return "redirect:/players";
    }

    @GetMapping("/players/search")
    public String searchPlayers(@RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                @RequestParam(defaultValue = "0") int page,
                                Model model) {

        int pageSize = 6;
        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate) : null;
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate) : null;

        Page<Player> playerPage = playerService.searchByNameAndDobRange(keyword, start, end, PageRequest.of(page, pageSize));

        model.addAttribute("players", playerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", playerPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "player/list";
    }

}
