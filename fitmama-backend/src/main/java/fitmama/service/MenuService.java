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

    public Menu find(Long id) {
        Optional<Menu> menuOpt = menuRepository.findById(id);
        return menuOpt.isPresent() ? menuOpt.get() : null;
    }

    public Menu add(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    public Menu update(Menu menu) {

        Optional<Menu> menuDBOpt = menuRepository.findById(menu.getId());
        if (menuDBOpt.isPresent()) {
            Menu menuDB = menuDBOpt.get();
            menuDB.setName(menu.getName());
            return menuRepository.saveAndFlush(menuDB);
        }

        return menu;
    }

    public Menu updateUsers(Menu menu) {
        Optional<Menu> menuDBOpt = menuRepository.findById(menu.getId());
        if (menuDBOpt.isPresent()) {
            Menu menuDB = menuDBOpt.get();

            menu.getUsers().forEach(user -> {
                if (!menuDB.getUsers().contains(user)) {
                    Optional<User> userDBOpt = userRepository.findById(user.getId());
                    if (userDBOpt.isPresent()) {
                        User userDB = userDBOpt.get();
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
        Optional<Menu> menuDBOpt = menuRepository.findById(id);
        if (menuDBOpt.isPresent()) {
            Menu menuDB = menuDBOpt.get();
            menuDB.getUsers().clear();
            menuRepository.deleteById(id);
        }
    }

    public void addDay(Long menuid, MenuDay menuDay) {
        Optional<Menu> menuOptional = menuRepository.findById(menuid);

        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            menuDay.setId(null);
            menuDay.setMenu(menu);
            menu.getMenuDays().add(menuDay);
            menuRepository.saveAndFlush(menu);
        }
    }

    public void updateDay(Long menuid, MenuDay menuDay) {
        Optional<Menu> menuOptional = menuRepository.findById(menuid);

        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();

            for (MenuDay day : menu.getMenuDays()) {
                if (day.getId().equals(menuDay.getId())) {
                    day.setDay(menuDay.getDay());
                    day.setName(menuDay.getName());
                    //day.setContent(menuDay.getContent());
                    menuDayRepository.saveAndFlush(day);
                }
            }
        }
    }

    public void removeDay(Long dayid) {
        menuDayRepository.deleteById(dayid);
    }

    public void addUser(Long menuid, Long userid) {
        Optional<Menu> menuOptional = menuRepository.findById(menuid);
        Optional<User> userOptional = userRepository.findById(userid);

        if (menuOptional.isPresent() && userOptional.isPresent()) {
            Menu menu = menuOptional.get();
            User user = userOptional.get();

            if (!exists(menu, user)) {
                menu.getUsers().add(user);
                menuRepository.saveAndFlush(menu);
            }
        }
    }

    public void removeUser(Long menuid, Long userid) {
        Optional<Menu> menuOptional = menuRepository.findById(menuid);
        Optional<User> userOptional = userRepository.findById(userid);

        if (menuOptional.isPresent() && userOptional.isPresent()) {
            Menu menu = menuOptional.get();
            User user = userOptional.get();

            if (exists(menu, user)) {
                menu.getUsers().remove(user);
                menuRepository.saveAndFlush(menu);
            }
        }
    }

    private boolean exists(Menu menu, User user) {
        for (User menuUser : menu.getUsers()) {
            if (menuUser.getId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

}
