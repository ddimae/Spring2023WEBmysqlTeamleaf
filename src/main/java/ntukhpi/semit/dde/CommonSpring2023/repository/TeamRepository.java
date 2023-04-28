package ntukhpi.semit.dde.CommonSpring2023.repository;

import ntukhpi.semit.dde.CommonSpring2023.entity.ProgramLanguage;
import ntukhpi.semit.dde.CommonSpring2023.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    Team findByTeamCod(String teamCod);

    List<Team> findALLByPl(ProgramLanguage java);
}
