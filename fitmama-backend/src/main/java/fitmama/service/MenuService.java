package fitmama.service;

import fitmama.model.Menu;
import fitmama.model.MenuDay;
import fitmama.model.User;
import fitmama.repo.MenuDayRepository;
import fitmama.repo.MenuRepository;
import fitmama.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuDayRepository menuDayRepository;

    public List<Menu> findAll() {
        return menuRepository.findAllByOrderByIdAsc();
    }

    public Menu add(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    public Menu update(Menu menu) {
        Menu menuDB = getMenu(menu.getId());
        if (menuDB != null) {
            menuDB.setName(menu.getName());
            return menuRepository.saveAndFlush(menuDB);
        }
        return menu;
    }

    public Menu updateUsers(Menu menu) {
        Menu menuDB = getMenu(menu.getId());
        if (menuDB != null) {
            menu.getUsers().forEach(user -> {
                if (!menuDB.getUsers().contains(user)) {
                    User userDB = getUser(user.getId());
                    if (userDB != null) {
                        menuDB.getUsers().add(userDB);
                    }
                }
            });
            menuDB.getUsers().retainAll(menu.getUsers());
            return menuRepository.saveAndFlush(menuDB);
        }
        return null;
    }

    public void delete(Long id) {
        Menu menu = getMenu(id);
        if (menu != null) {
            menu.getUsers().clear();
            menuRepository.deleteById(id);
        }
    }

    public MenuDay addDay(Long menuid, MenuDay menuDay) {
        Menu menu = getMenu(menuid);
        if (menu != null) {

            MenuDay dayFound = null;
            for (MenuDay day : menu.getMenuDays()) {
                if (day.getDay().equals(menuDay.getDay())) {
                    dayFound = day;
                    break;
                }
            }

            if (dayFound != null) {
                dayFound.setName(menuDay.getName());
                menuDayRepository.saveAndFlush(dayFound);
            } else {
                menuDay.setId(null);
                menuDay.setMenu(menu);
                menuDayRepository.saveAndFlush(menuDay);
            }
        }

        return menuDay;
    }

    public void updateDay(MenuDay menuDay) {
        MenuDay day = getMenuDay(menuDay.getId());
        if (day != null) {
            day.setName(menuDay.getName());
            menuDayRepository.saveAndFlush(day);
        }
    }

    public void removeDay(Long dayid) {
        menuDayRepository.deleteById(dayid);
    }

    public MenuDay copyDay(Long dayid, MenuDay menuDay) {
        MenuDay daySource = getMenuDay(dayid);
        if (daySource != null) {
            menuDay.setContent(daySource.getContent());
            menuDay.setName(daySource.getName());
            menuDay.setMenu(daySource.getMenu());
            menuDayRepository.saveAndFlush(menuDay);
        }
        return menuDay;
    }

    public void addUser(Long menuid, Long userid) {
        Menu menu = getMenu(menuid);
        User user = getUser(userid);

        if (!exists(menu, user)) {
            menu.getUsers().add(user);
            menuRepository.saveAndFlush(menu);
        }
    }

    public void removeUser(Long menuid, Long userid) {
        Menu menu = getMenu(menuid);
        User user = getUser(userid);

        if (exists(menu, user)) {
            menu.getUsers().remove(user);
            menuRepository.saveAndFlush(menu);
        }
    }

    private boolean exists(Menu menu, User user) {
        if (menu != null && user != null) {
            for (User menuUser : menu.getUsers()) {
                if (menuUser.getId().equals(user.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getContent(Long menuDayId) {
        MenuDay day = getMenuDay(menuDayId);
        return day != null ? day.getContent() : null;
    }

    public void setContent(Long menuDayId, String content) {
        MenuDay day = getMenuDay(menuDayId);
        if (day != null) {
            day.setContent(content);
            menuDayRepository.saveAndFlush(day);
        }
    }

    public Menu getMenu(Long id) {
        Optional<Menu> menuOpt = menuRepository.findById(id);
        return menuOpt.isPresent() ? menuOpt.get() : null;
    }

    public MenuDay getMenuDay(Long id) {
        Optional<MenuDay> menuDayOpt = menuDayRepository.findById(id);
        return menuDayOpt.isPresent() ? menuDayOpt.get() : null;
    }

    public User getUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.isPresent() ? userOpt.get() : null;
    }
}
