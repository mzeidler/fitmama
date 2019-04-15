package fitmama.controller;

import fitmama.model.User;
import fitmama.model.UserGroup;
import fitmama.model.UserGroupJoin;
import fitmama.repo.UserGroupJoinRepository;
import fitmama.service.UserGroupJoinService;
import fitmama.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserGroupController {

	@Autowired
	private UserGroupService service;

	@Autowired
	private UserGroupJoinService joinService;

	@GetMapping("/api/groups")
	public List<UserGroup> findAll() {
		return service.findAll();
	}

	@GetMapping("/api/group/{id}")
	public UserGroup findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping("/api/group/{id}/members")
	public List<UserGroupJoin> findByGroupId(@PathVariable Long id) {
		return joinService.findByGroupId(id);
	}

	@PostMapping("/api/group/save")
	public UserGroup save(@RequestBody UserGroup group) {
		return service.save(group);
	}

	@DeleteMapping("/api/group/{id}/delete")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
