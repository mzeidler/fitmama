package fitmama.app;

import fitmama.model.*;
import fitmama.service.MenuService;
import fitmama.service.RoleService;
import fitmama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {
		"fitmama.controller",
		"fitmama.service",
		"fitmama.app"
})
@EnableJpaRepositories("fitmama.repo")
@EnableAutoConfiguration
@EntityScan("fitmama.model")
public class FitmamaBackendApplication {

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(FitmamaBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			List<Role> roles = roleService.findAll();
			if (roles.isEmpty()) {
				roleService.saveRole(createRole(RoleKey.ADMIN, "Administratorica"));
				roleService.saveRole(createRole(RoleKey.EXERCISE_ADMIN, "Trenerica"));
				roleService.saveRole(createRole(RoleKey.MENU_ADMIN, "Nutricionistica"));
				roleService.saveRole(createRole(RoleKey.USER, "Korisnica"));
			}

			List<User> users = userService.findAll();
			if (users.isEmpty()) {
				User admin = new User();
				admin.setLastName("Administrator");
				admin.setUsername("admin");
				userService.saveUser(admin);
			}
		};

		// MySQL
		// schema collation: latin2 - default collation
		// table collation: latin2 - latin2_croatian_ci
		// user.address collation: utf8 - default collation
		// spring.datasource.url=jdbc:mysql://localhost:3306/fitmama?serverTimezone=UTC&useUnicode=yes&characterEncoding=utf-8
	}

	private Role createRole(RoleKey key, String name) {
		Role role = new Role();
		role.setRoleKey(key);
		role.setName(name);
		return role;
	}


}
