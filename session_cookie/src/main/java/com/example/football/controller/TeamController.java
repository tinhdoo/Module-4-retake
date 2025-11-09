package com.example.football.controller;

import com.example.football.entity.Team;
import com.example.football.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TeamController{
    @Autowired
    private ITeamService teamService;

    @GetMapping("/teams")
    public String listTeams(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size,
                            @RequestParam(required = false) String keyword,
                            Model model) {

        Page<Team> teamPage;

        if (keyword == null || keyword.trim().isEmpty()) {
            teamPage = teamService.getAll(PageRequest.of(page, size));
        } else {
            teamPage = teamService.searchByName(keyword, PageRequest.of(page, size));
        }

        model.addAttribute("teams", teamPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", teamPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "team/list-team";
    }

    @GetMapping("/team/team-add")
    public String showAddTeamForm(Model model){
        model.addAttribute("team", new Team());
        return "team/team-add";
    }

    @PostMapping("/team/team-add")
    public String addTeam(@ModelAttribute("team") Team team, RedirectAttributes redirectAttributes){
        teamService.add(team);
        redirectAttributes.addFlashAttribute("mess", "Thêm đội bóng thành công");
        return "redirect:/teams";
    }

    // BỔ SUNG: Method xử lý xóa
    @GetMapping("/teams/delete/{teamId}")
    public String deleteTeam(@PathVariable("teamId") Integer teamId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mess", "Chức năng Xóa đang được phát triển!");
        return "redirect:/teams";
    }

    @GetMapping("/teams/edit/{teamId}")
    public String showEditTeamForm(@PathVariable("teamId") Integer teamId, Model model) {
        return "redirect:/teams";
    }
    @GetMapping("/teams/{teamId}")
    public String viewTeam(@PathVariable("teamId") Integer teamId, Model model) {
        Optional<Team> teamOpt = teamService.findById(teamId);

        if (teamOpt.isPresent()) {
            model.addAttribute("team", teamOpt.get());
        } else {
            model.addAttribute("team", null);
        }
        return "team/team-detail";
    }
}
