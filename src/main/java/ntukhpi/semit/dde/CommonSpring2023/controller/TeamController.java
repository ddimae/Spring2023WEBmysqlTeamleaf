package ntukhpi.semit.dde.CommonSpring2023.controller;

import ntukhpi.semit.dde.CommonSpring2023.entity.Team;

import ntukhpi.semit.dde.CommonSpring2023.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;

    // Insert into constructor NECESSARY
    public TeamController(TeamService teamService) {
        super();
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public String showAllTeams(Model model) {
        //Get data from DB
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);
        return "teams";
    }

}


