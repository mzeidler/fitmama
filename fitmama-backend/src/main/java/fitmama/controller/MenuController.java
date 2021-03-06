package fitmama.controller;

import fitmama.model.*;
import fitmama.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
		return menuService.getMenu(id);
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
	public MenuDay addDay(@PathVariable Long menuid, @RequestBody MenuDay menuDay) {
		return menuService.addDay(menuid, menuDay);
	}

	@PostMapping("/api/menus/updateday")
	public void updateDay(@RequestBody MenuDay menuDay) {
		menuService.updateDay(menuDay);
	}

	@PostMapping("/api/menus/copyday/{dayid}")
	public MenuDay copyDay(@PathVariable Long dayid, @RequestBody MenuDay menuDay) {
		return menuService.copyDay(dayid, menuDay);
	}

	@PostMapping("/api/menus/removeday/{dayid}")
	public void removeDay(@PathVariable Long dayid) {
		menuService.removeDay(dayid);
	}

	@GetMapping(value = "/api/menuday/content/{menuDayId}", produces = MediaType.TEXT_PLAIN_VALUE )
	public String getContent(@PathVariable Long menuDayId) {
		return menuService.getContent(menuDayId);
	}

	@GetMapping(value = "/api/menus/content/{menuId}/{day}" )
	public DayContent getDayContent(@PathVariable Long menuId, @PathVariable String day) {
		return menuService.getDayContent(menuId, day);
	}

	@PostMapping("/api/menuday/content/{menuDayId}")
	public void setContent(@PathVariable Long menuDayId, @RequestBody String content) {
		menuService.setContent(menuDayId, content);
	}

}
