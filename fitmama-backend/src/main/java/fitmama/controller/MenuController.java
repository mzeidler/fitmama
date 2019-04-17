package fitmama.controller;

import fitmama.model.Menu;
import fitmama.model.User;
import fitmama.model.UserGroupJoin;
import fitmama.service.MenuService;
import fitmama.service.UserGroupJoinService;
import fitmama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping("/api/menus")
	public List<Menu> findAll() {
		return menuService.findAll();
	}

	@PostMapping("/api/menus/add")
	public Menu add(@RequestBody Menu menu) {
		return menuService.add(menu);
	}

	@PostMapping("/api/menus/update")
	public Menu update(@RequestBody Menu menu) {
		return menuService.update(menu);
	}

	@DeleteMapping("/api/menus/{id}/delete")
	public void delete(@PathVariable Long id) {
		menuService.delete(id);
	}

	@PostMapping("/api/menus/{menuid}/adduser/{userid}")
	public void addUser(@PathVariable Long menuid, @PathVariable Long userid) {
		menuService.addUser(menuid, userid);
	}

	@PostMapping("/api/menus/{menuid}/removeuser/{userid}")
	public void removeUser(@PathVariable Long menuid, @PathVariable Long userid) {
		menuService.removeUser(menuid, userid);
	}


	//******************************************************
	// TEST
	//******************************************************
	@GetMapping("/api/menus/test")
	public String test() {
		return menuService.test();
	}
}
