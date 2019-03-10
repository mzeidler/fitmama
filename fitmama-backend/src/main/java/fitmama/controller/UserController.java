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
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private UserGroupJoinService joinService;

	@GetMapping("/users")
	public Flux<User> findAll() {
		return service.findAll();
	}

	@GetMapping("/user/{id}")
	public Mono<User> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping("/user/{id}/groups")
	public Flux<UserGroupJoin> findByUserId(@PathVariable Long id) {
		return joinService.findByUserId(id);
	}

	@PostMapping("/user/save")
	public Mono<User> save(@RequestBody User user) {
		return service.save(user);
	}

	@DeleteMapping("/user/{id}/delete")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
