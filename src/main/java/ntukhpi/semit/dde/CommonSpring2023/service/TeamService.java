package ntukhpi.semit.dde.CommonSpring2023.service;

import ntukhpi.semit.dde.CommonSpring2023.entity.ProgramLanguage;
import ntukhpi.semit.dde.CommonSpring2023.entity.Team;

import java.util.List;

public interface TeamService {
	List<Team> getAllTeams();
    Team getTeamById(Long id);
    Team saveTeam(Team team);
    Team updateTeam(Team team);
    void deleteTeamById(Long id);
    //additional queries
    Team getTeamByName(String teamCod);

    List<Team> getAllTeamsByPL(ProgramLanguage java);
}
