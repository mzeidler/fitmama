package fitmama.service;

import fitmama.model.Menu;
import fitmama.model.Role;
import fitmama.model.Training;
import fitmama.repo.MenuRepository;
import fitmama.repo.RoleRepository;
import fitmama.repo.TrainingRepository;
import fitmama.util.ListDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitmama.model.User;
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
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long id) {
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
