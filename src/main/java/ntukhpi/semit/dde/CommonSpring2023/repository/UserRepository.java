package ntukhpi.semit.dde.CommonSpring2023.repository;

import ntukhpi.semit.dde.CommonSpring2023.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

     Optional<User> findUserByUsername(String username);

     Boolean existsByEmail(String email);

     Optional<User> findByUsernameOrEmail(String username, String email);

     boolean existsByUsername(String username);


}
