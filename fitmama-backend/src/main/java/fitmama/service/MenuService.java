package fitmama.service;

import fitmama.model.Menu;
import fitmama.model.MenuDay;
import fitmama.model.User;
import fitmama.repo.MenuRepository;
import fitmama.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }


    //******************************************************
    // TEST
    //******************************************************
    public String test() {

        Menu menu1 = getMenu("First Menu");
        Menu menu2 = getMenu("Second Menu");
        Menu menu3 = getMenu("Third Menu");

        menu1.setUsers(userRepository.findAll());
        menu2.setUsers(userRepository.findAll());
        menu3.setUsers(userRepository.findAll());

        menuRepository.saveAndFlush(menu1);
        menuRepository.saveAndFlush(menu2);
        menuRepository.saveAndFlush(menu3);

        return null;
    }

    private Menu getMenu(String name) {
        Menu menu = new Menu();
        menu.setName(name);
        menu.setMenuDays(getMenuDays(menu));
        return menu;
    }

    private List<MenuDay> getMenuDays(Menu menu) {
        List<MenuDay> menuDays = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        menuDays.add(getMenuDay(Date.valueOf(localDate.minusDays(2)), "MenuDay1", menu));
        menuDays.add(getMenuDay(Date.valueOf(localDate.minusDays(1)), "MenuDay2", menu));
        menuDays.add(getMenuDay(Date.valueOf(localDate), "MenuDay3", menu));
        menuDays.add(getMenuDay(Date.valueOf(localDate.plusDays(1)), "MenuDay4", menu));
        menuDays.add(getMenuDay(Date.valueOf(localDate.plusDays(2)), "MenuDay5", menu));
        menuDays.add(getMenuDay(Date.valueOf(localDate.plusDays(3)), "MenuDay6", menu));
        return menuDays;
    }

    private MenuDay getMenuDay(Date day, String name, Menu menu) {
        MenuDay menuDay = new MenuDay();
        menuDay.setContent("Content for " + name);
        menuDay.setName(name);
        menuDay.setMenu(menu);
        menuDay.setDay(day);
        return menuDay;
    }

}
