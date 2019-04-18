package fitmama.controller;

import fitmama.model.User;
import fitmama.model.UserGroupJoin;
import fitmama.model.Users;
import fitmama.repo.UserGroupJoinRepository;
import fitmama.repo.UserRepository;
import fitmama.service.UserGroupJoinService;
import fitmama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserGroupJoinService joinService;

	/**
	 * Users
	 */
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

	@DeleteMapping("/api/user/{id}/delete")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	/**
	 * Groups
	 */
	@GetMapping("/user/{id}/groups")
	public List<UserGroupJoin> findGroupsByUserId(@PathVariable Long id) {
		return joinService.findByUserId(id);
	}

	@PostMapping("/user/{userid}/join/{groupid}")
	public void joinToGroup(@PathVariable Long userid, @PathVariable Long groupid) {
		joinService.join(userid, groupid);
	}

	@PostMapping("/user/{userid}/leave/{groupid}")
	public void leaveGroup(@PathVariable Long userid, @PathVariable Long groupid) {
		joinService.leave(userid, groupid);
	}

}
