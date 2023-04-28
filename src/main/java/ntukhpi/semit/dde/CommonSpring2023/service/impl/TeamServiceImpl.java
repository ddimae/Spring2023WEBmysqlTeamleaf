package ntukhpi.semit.dde.CommonSpring2023.service.impl;

import ntukhpi.semit.dde.CommonSpring2023.entity.Team;
import ntukhpi.semit.dde.CommonSpring2023.repository.TeamRepository;
import ntukhpi.semit.dde.CommonSpring2023.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        super();
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id).get();
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }


    @Override
    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeamById(Long id) {
         teamRepository.deleteById(id);
    }

//    @Override
//    public Team getTeamByName(String teamCode) {
//        return teamRepository.getTeamByTeamCod(teamCode);
//    }
}
