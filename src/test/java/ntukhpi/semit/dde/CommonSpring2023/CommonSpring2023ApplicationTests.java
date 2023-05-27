package ntukhpi.semit.dde.CommonSpring2023;

import ntukhpi.semit.dde.CommonSpring2023.entity.auth.Role;
import ntukhpi.semit.dde.CommonSpring2023.entity.auth.User;
import ntukhpi.semit.dde.CommonSpring2023.repository.RoleRepository;
import ntukhpi.semit.dde.CommonSpring2023.repository.UserRepository;
import ntukhpi.semit.dde.CommonSpring2023.utils.EmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static ntukhpi.semit.dde.CommonSpring2023.SpringSecurityConfig.passwordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommonSpring2023ApplicationTests {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testSendEmail() {
		EmailSender.send();
	}
	@Test
	void testSendEmail2() {
		EmailSender.sendEmail("dde.semit.ntukhpi@gmail.com", "employees.xlsx");
	}


	@Test
	void insertUsersRolesTest(){
		//============= add roles if its not on db
		Role admin = null;
		Optional<Role> adminOpt = roleRepository.findByName("ROLE_ADMIN");
		if (!adminOpt.isPresent()) {
			admin = new Role(1L, "ROLE_ADMIN");
			roleRepository.save(admin);
		} else {
			admin = adminOpt.get();
		}
		Role user = null;
		Optional<Role> userOpt =roleRepository.findByName("ROLE_USER");
		if (!userOpt.isPresent()) {
			user = new Role(2L, "ROLE_USER");
			roleRepository.save(user);
		} else {
			user = userOpt.get();
		}
		assertEquals(2,roleRepository.findAll().size());

		Set<Role> rolesSetAdmin = new LinkedHashSet<>();
		rolesSetAdmin.add(admin);
		Set<Role> rolesSetUser = new LinkedHashSet<>();
		rolesSetUser.add(user);

		User user1 = null;
		Optional<User> optionalUser1 = null;
		String passwordAdmin = passwordEncoder().encode("admin");
		System.out.println(passwordAdmin);
		if (userRepository.existsByUsername("admin")) {
			optionalUser1 = userRepository.findUserByUsername("admin");
			user1 = optionalUser1.get();
		} else {
			if (userRepository.existsByEmail("admin@gmail.com")) {
				optionalUser1 = userRepository.findByUsernameOrEmail("admin", "admin@gmail.com");
				user1 = optionalUser1.get();
			}	else {
				user1 = new User(1L,"DDimaE72","admin","admin@gmail.com",passwordAdmin,rolesSetAdmin);
				userRepository.save(user1);
			}
		}

		User user2 = null;
		Optional<User> optionalUser2 = null;
		String passwordUser = passwordEncoder().encode("ramesh");
		System.out.println(passwordUser);
		if (userRepository.existsByUsername("ramesh")) {
			optionalUser2 = userRepository.findUserByUsername("ramesh");
			user2 = optionalUser2.get();
		} else {
			if (userRepository.existsByEmail("ramesh@gmail.com")) {
				optionalUser1 = userRepository.findByUsernameOrEmail("ramesh", "ramesh@gmail.com");
				user2 = optionalUser2.get();
			}	else {
				user2 = new User(2L,"ramesh","ramesh","ramesh@gmail.com",passwordUser,rolesSetUser);
				userRepository.save(user2);
			}
		}

		assertEquals(2,userRepository.findAll().size());
		user1 = userRepository.findUserByUsername("admin").get();
		assertEquals(user1.getRoles().size(),1);
		user2 = userRepository.findByUsernameOrEmail("", "ramesh@gmail.com").get();
		assertEquals(user2.getRoles().size(),1);
	}


}
