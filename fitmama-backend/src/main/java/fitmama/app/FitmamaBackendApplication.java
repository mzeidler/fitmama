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



			List<User> users = userService.findAll();
			if (users.isEmpty()) {
				User user1 = createUser("Hans","Müller", "hmueller");
				User user2 = createUser("Thomas","Mustermann", "tmustermann");
				User user3 = createUser("Günter","Bauer", "gbauer");
				User admin = createUser("Administrator",null, "admin");

				userService.saveUser(user1);
				userService.saveUser(user2);
				userService.saveUser(user3);
				userService.saveUser(admin);
			}

			List<Role> roles = roleService.findAll();
			if (roles.isEmpty()) {
				roleService.saveRole(createRole(RoleKey.ADMIN));
				roleService.saveRole(createRole(RoleKey.EXERCISE_ADMIN));
				roleService.saveRole(createRole(RoleKey.MENU_ADMIN));
				roleService.saveRole(createRole(RoleKey.USER));

				roleService.findAll().forEach(role -> {
					role.setUsers(userService.findAll());
					roleService.saveRole(role);
				});
			}

			List<Menu> menus = menuService.findAll();
			if (menus.isEmpty()) {
				Menu menu1 = createMenu("First Menu");
				Menu menu2 = createMenu("Second Menu");
				Menu menu3 = createMenu("Third Menu");

				menuService.add(menu1);
				menuService.add(menu2);
				menuService.add(menu3);

				menuService.findAll().forEach(menu -> {
					menu.setUsers(userService.findAll());
					menuService.add(menu);
				});
			}

		};
	}

	private Role createRole(RoleKey key) {
		Role role = new Role();
		role.setRoleKey(key);
		return role;
	}

	private User createUser(String firstname, String lastname, String username) {
		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setUsername(username);
		return user;
	}

	private Menu createMenu(String name) {
		Menu menu = new Menu();
		menu.setName(name);
		menu.setMenuDays(createMenuDays(menu));
		return menu;
	}

	private List<MenuDay> createMenuDays(Menu menu) {
		List<MenuDay> menuDays = new ArrayList<>();
		menuDays.add(createMenuDay("2019-04-10", "MenuDay1", menu));
		menuDays.add(createMenuDay("2019-04-11", "MenuDay2", menu));
		menuDays.add(createMenuDay("2019-04-12", "MenuDay3", menu));
		menuDays.add(createMenuDay("2019-04-13", "MenuDay4", menu));
		menuDays.add(createMenuDay("2019-04-14", "MenuDay5", menu));
		menuDays.add(createMenuDay("2019-04-15", "MenuDay6", menu));
		return menuDays;
	}

	private MenuDay createMenuDay(String day, String name, Menu menu) {
		MenuDay menuDay = new MenuDay();
		menuDay.setContent("Content for " + name);
		menuDay.setName(name);
		menuDay.setMenu(menu);
		menuDay.setDay(day);
		return menuDay;
	}
}
