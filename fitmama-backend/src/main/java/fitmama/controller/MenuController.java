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

	@GetMapping("/api/menus/test")
	public String test() {
		return menuService.test();
	}
}
