package ntukhpi.semit.dde.CommonSpring2023.controller;

import ntukhpi.semit.dde.CommonSpring2023.entity.ProgramLanguage;
import ntukhpi.semit.dde.CommonSpring2023.entity.Team;

import ntukhpi.semit.dde.CommonSpring2023.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;

    // Insert into constructor NECESSARY
    public TeamController(TeamService teamService) {
        super();
        this.teamService = teamService;
    }

    //Handler for show page with team list
    @GetMapping("/teams")
    public String showAllTeams(Model model) {
        //Get data from DB
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);
        return "teams";
    }

    //Handler for opening form for creation of a Team
    @GetMapping("/teams/new")
    public String createTeamsForm(Model model) {
        // create Teams object to hold employee form data
        Team team = new Team(0l, "", ProgramLanguage.JAVA);
        model.addAttribute("newTeam", team);
        return "create_team";
    }

    //Handler for saving data about Team
    @PostMapping("/teams")
    public String saveTeam(@ModelAttribute("newTeam") Team team) {
        teamService.saveTeam(team);
        return "redirect:/teams";
    }

    //Handler for opening form for update Team
    @GetMapping("/teams/edit/{id}")
    public String editTeamForm(@PathVariable Long id, Model model) {
        model.addAttribute("team", teamService.getTeamById(id));
        return "edit_team";
    }

    //Handler for saving data after update Team
    @PostMapping("/teams/{id}")
    public String updateTeam(@PathVariable Long id,
                                 @ModelAttribute("team") Team team,
                                 Model model) {
        // get Team from database by id
        Team teamFromDB = teamService.getTeamById(id);
        //existingTeam.setId(id);
        //LOOK aT YOUR CLASS!!!!!!!
        teamFromDB.setTeamCod(team.getTeamCod());
        teamFromDB.setPl(team.getPl());
        // save updated Team object
        teamService.updateTeam(teamFromDB);
        return "redirect:/teams";
    }

    // Handler for delete Team by ID
    @GetMapping("/teams/{id}")
    public String deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return "redirect:/teams";
    }

}


