package ntukhpi.semit.dde.CommonSpring2023.repository;

import ntukhpi.semit.dde.CommonSpring2023.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    //Team getTeamByTeamCod(String teamCode);

}
