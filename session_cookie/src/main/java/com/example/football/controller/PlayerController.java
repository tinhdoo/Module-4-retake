package com.example.football.controller;

import com.example.football.dto.FavoriteTeamDto;
import com.example.football.dto.PlayerDto;
import com.example.football.entity.Player;
import com.example.football.service.PlayerService;
import com.example.football.service.TeamService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
                              @CookieValue(name = "viewMode", defaultValue = "grid") String viewMode,
                              Model model) {
        int pageSize = 6;
        Page<Player> playerPage = playerService.getAll(PageRequest.of(page, pageSize));

        model.addAttribute("players", playerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", playerPage.getTotalPages());

        model.addAttribute("viewMode", viewMode);

        if ("list".equals(viewMode)) {
            return "player/list-table";
        }
        return "player/list";
    }

    @GetMapping("/players/set-view")
    public String setViewMode(@RequestParam String viewMode,
                              HttpServletResponse response) {

        Cookie viewModeCookie = new Cookie("viewMode", viewMode);
        viewModeCookie.setMaxAge(30 * 24 * 60 * 60);
        viewModeCookie.setPath("/");
        viewModeCookie.setHttpOnly(true);
        viewModeCookie.setSecure(true);

        response.addCookie(viewModeCookie);

        return "redirect:/players";
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
    public String deletePlayer(@PathVariable("code") Integer code) {
        playerService.delete(code);
        return "redirect:/players";
    }


    @GetMapping("/players/add")
    public String addPlayerForm(Model model) {
        model.addAttribute("player", new PlayerDto());
        model.addAttribute("teams", teamService.getAll());
        return "player/add";
    }

    @PostMapping("/players/add")
    public String addPlayer(@Valid @ModelAttribute("player") PlayerDto playerDto,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        // Tính tuổi
        int age = playerDto.getAge();
        if (age < 16 || age > 100) {
            result.rejectValue("dob", "error.player", "Tuổi phải từ 16 đến 100");
        }

        if (result.hasErrors()) {
            model.addAttribute("teams", teamService.getAll());
            return "player/add";
        }

        Player player = new Player();
        player.setName(playerDto.getName());
        player.setDob(playerDto.getDob());
        player.setExperience(String.valueOf(playerDto.getExperience()));
        player.setPosition(playerDto.getPosition());
        player.setImage(playerDto.getImage());
        player.setTeam(playerDto.getTeam());

        playerService.save(player);
        redirectAttributes.addFlashAttribute("mess", "Thêm cầu thủ thành công!");
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

    private FavoriteTeamDto getFavoriteTeam(HttpSession session) {
        FavoriteTeamDto favoriteTeam = (FavoriteTeamDto) session.getAttribute("favoriteTeam");
        if (favoriteTeam == null) {
            favoriteTeam = new FavoriteTeamDto();
            session.setAttribute("favoriteTeam", favoriteTeam);
        }
        return favoriteTeam;
    }

    @GetMapping("/players/add-to-favorite/{code}")
    public String addToFavorite(@PathVariable("code") Integer code,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        Optional<Player> playerOpt = playerService.findByCode(code);
        if (playerOpt.isPresent()) {
            FavoriteTeamDto favoriteTeam = getFavoriteTeam(session);
            favoriteTeam.addPlayer(playerOpt.get());
            session.setAttribute("favoriteTeam", favoriteTeam);
            redirectAttributes.addFlashAttribute("mess", "Đã thêm " + playerOpt.get().getName() + " vào đội hình!");
        }
        return "redirect:/players/" + code;
    }

    @GetMapping("/players/favorite-team")
    public String showFavoriteTeam(HttpSession session, Model model) {
        FavoriteTeamDto favoriteTeam = getFavoriteTeam(session);
        model.addAttribute("favoriteTeam", favoriteTeam);
        return "player/favorite-team";
    }

    @GetMapping("/players/remove-from-favorite/{code}")
    public String removeFromFavorite(@PathVariable("code") Integer code, HttpSession session) {
        FavoriteTeamDto favoriteTeam = getFavoriteTeam(session);
        favoriteTeam.removePlayer(code);
        session.setAttribute("favoriteTeam", favoriteTeam);
        return "redirect:/players/favorite-team";
    }
}
