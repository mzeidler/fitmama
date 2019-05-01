package fitmama.service;

import fitmama.model.*;
import fitmama.repo.MenuRepository;
import fitmama.repo.RoleRepository;
import fitmama.repo.TrainingRepository;
import fitmama.util.ListDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitmama.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("User with id=" + id + " does not exist");
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            User userDB = userRepository.saveAndFlush(user);

            Role userRole = roleRepository.findByRoleKey(RoleKey.USER).get(0);
            userRole.getUsers().add(userDB);
            roleRepository.saveAndFlush(userRole);

            userDB.getRoles().add(userRole);
            return userDB;
        } else {
            User userDB = getUser(user.getId());
            userDB.setUsername(user.getUsername());
            userDB.setFirstName(user.getFirstName());
            userDB.setLastName(user.getLastName());
            userDB.setAddress(user.getAddress());
            userDB.setCity(user.getCity());
            userDB.setEmail(user.getEmail());
            userDB.setHeight(user.getHeight());
            userDB.setBirthDate(user.getBirthDate());
            userDB.setMobile(user.getMobile());
            userDB.setZipcode(user.getZipcode());
            userDB.setGender(user.getGender());
            return userRepository.saveAndFlush(userDB);
        }
    }

    public void deleteUser(Long id) {
        User userDB = getUser(id);

        for (Role role : userDB.getRoles()) {
            role.getUsers().remove(userDB);
            roleRepository.saveAndFlush(role);
        }

        for (Menu menu : userDB.getMenus()) {
            menu.getUsers().remove(userDB);
            menuRepository.saveAndFlush(menu);
        }

        for (Training training : userDB.getTrainings()) {
            training.getUsers().remove(userDB);
            trainingRepository.saveAndFlush(training);
        }

        userRepository.deleteById(id);
    }

    public User updateTrainings(User user) {
        User userDB = getUser(user.getId());

        ListDiff<Training> diff = new ListDiff(user.getTrainings(), userDB.getTrainings());

        diff.leftOnly().forEach(trainingToAdd -> {
            Training trainingDB = getTraining(trainingToAdd.getId());
            trainingDB.getUsers().add(userDB);
            trainingRepository.saveAndFlush(trainingDB);
        });

        diff.rightOnly().forEach(trainingToRemove -> {
            Training trainingDB = getTraining(trainingToRemove.getId());
            trainingDB.getUsers().remove(userDB);
            trainingRepository.saveAndFlush(trainingDB);
        });

        return userDB;
    }

    public User updateMenus(User user) {
        User userDB = getUser(user.getId());

        ListDiff<Menu> diff = new ListDiff(user.getMenus(), userDB.getMenus());

        diff.leftOnly().forEach(menuToAdd -> {
            Menu menuDB = getMenu(menuToAdd.getId());
            menuDB.getUsers().add(userDB);
            menuRepository.saveAndFlush(menuDB);
        });

        diff.rightOnly().forEach(menuToRemove -> {
            Menu menuDB = getMenu(menuToRemove.getId());
            menuDB.getUsers().remove(userDB);
            menuRepository.saveAndFlush(menuDB);
        });

        return userDB;
    }

    public User updateRoles(User user) {
        User userDB = getUser(user.getId());

        ListDiff<Role> diff = new ListDiff(user.getRoles(), userDB.getRoles());

        diff.leftOnly().forEach(roleToAdd -> {
            Role roleDB = getRole(roleToAdd.getId());
            roleDB.getUsers().add(userDB);
            roleRepository.saveAndFlush(roleDB);
        });

        diff.rightOnly().forEach(roleToRemove -> {
            Role roleDB = getRole(roleToRemove.getId());
            roleDB.getUsers().remove(userDB);
            roleRepository.saveAndFlush(roleDB);
        });

        return userDB;
    }
    // Helper methods

    public User getUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.isPresent() ? userOpt.get() : null;
    }

    public Training getTraining(Long id) {
        Optional<Training> trainingOpt = trainingRepository.findById(id);
        return trainingOpt.isPresent() ? trainingOpt.get() : null;
    }

    public Menu getMenu(Long id) {
        Optional<Menu> menuOpt = menuRepository.findById(id);
        return menuOpt.isPresent() ? menuOpt.get() : null;
    }

    public Role getRole(Long id) {
        Optional<Role> roleOpt = roleRepository.findById(id);
        return roleOpt.isPresent() ? roleOpt.get() : null;
    }

}
