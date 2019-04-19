package fitmama.controller;

import fitmama.model.*;
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

	@GetMapping("/api/menus/{id}")
	public Menu find(@PathVariable Long id) {
		return menuService.find(id);
	}

	@GetMapping("/api/menus/short")
	public Menus findAllShort() {
		return new Menus(menuService.findAll());
	}

	@PostMapping("/api/menus/add")
	public Menu add(@RequestBody Menu menu) {
		return menuService.add(menu);
	}

	@PostMapping("/api/menus/update")
	public Menu update(@RequestBody Menu menu) {
		return menuService.update(menu);
	}

	@DeleteMapping("/api/menus/delete/{id}")
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
	@PostMapping("/api/menus/updateusers")
	public Menu updateUsers(@RequestBody Menu menu) {
		return menuService.updateUsers(menu);
	}

	@PostMapping("/api/menus/{menuid}/addday")
	public void addDay(@PathVariable Long menuid, @RequestBody MenuDay menuDay) {
		menuService.addDay(menuid, menuDay);
	}

	@PostMapping("/api/menus/{menuid}/updateday")
	public void updateDay(@PathVariable Long menuid, @RequestBody MenuDay menuDay) {
		menuService.updateDay(menuid, menuDay);
	}

	@PostMapping("/api/menus/removeday/{dayid}")
	public void removeDay(@PathVariable Long dayid) {
		menuService.removeDay(dayid);
	}

	//******************************************************
	// TEST
	//******************************************************
	@GetMapping("/api/menus/test")
	public String test() {
		return menuService.test();
	}
}
