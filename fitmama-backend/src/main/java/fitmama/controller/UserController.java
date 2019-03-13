package fitmama.controller;

import fitmama.model.User;
import fitmama.model.UserGroupJoin;
import fitmama.repo.UserGroupJoinRepository;
import fitmama.repo.UserRepository;
import fitmama.service.UserGroupJoinService;
import fitmama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
	@GetMapping("/users")
	public Flux<User> findAll() {
		return userService.findAll();
	}


	@GetMapping("/user/{id}")
	public Mono<User> findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping("/user/save")
	public Mono<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@DeleteMapping("/user/{id}/delete")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	/**
	 * Groups
	 */
	@GetMapping("/user/{id}/groups")
	public Flux<UserGroupJoin> findGroupsByUserId(@PathVariable Long id) {
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
