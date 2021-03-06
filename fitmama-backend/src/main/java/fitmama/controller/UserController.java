package fitmama.controller;

import fitmama.model.Menu;
import fitmama.model.User;
import fitmama.model.Users;
import fitmama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/api/users")
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/api/users/short")
	public Users findAllShort() {
		return new Users(userService.findAll());
	}

	@GetMapping("/api/user/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping("/api/user/save")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@DeleteMapping("/api/users/delete/{id}")
	public void delete(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@PostMapping("/api/users/updatetrainings")
	public User updateTrainings(@RequestBody User user) {
		return userService.updateTrainings(user);
	}

	@PostMapping("/api/users/updatemenus")
	public User updateMenus(@RequestBody User user) {
		return userService.updateMenus(user);
	}

	@PostMapping("/api/users/updateroles")
	public User updateRoles(@RequestBody User user) {
		return userService.updateRoles(user);
	}

	@PostMapping("/api/user/authenticate")
	public User authenticate(@RequestBody User user) {
		return userService.getAuthenticatedUser(user);
	}

}
