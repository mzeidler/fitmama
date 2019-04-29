package fitmama.service;

import fitmama.model.Menu;
import fitmama.model.Role;
import fitmama.model.Training;
import fitmama.repo.MenuRepository;
import fitmama.repo.RoleRepository;
import fitmama.repo.TrainingRepository;
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
        if (userDB != null) {
            user.getTrainings().forEach(training -> {
                if (!userDB.getTrainings().contains(training)) {
                    Training trainingDB = getTraining(training.getId());
                    if (trainingDB != null) {
                        userDB.getTrainings().add(trainingDB);
                    }
                }
            });
            userDB.getTrainings().retainAll(user.getTrainings());
            return userRepository.saveAndFlush(userDB);
        }
        return null;
    }

    public User updateMenus(User user) {
        User userDB = getUser(user.getId());
        if (userDB != null) {
            user.getMenus().forEach(menu -> {
                if (!userDB.getMenus().contains(menu)) {
                    Menu menuDB = getMenu(menu.getId());
                    if (menuDB != null) {
                        userDB.getMenus().add(menuDB);
                    }
                }
            });
            userDB.getMenus().retainAll(user.getMenus());
            return userRepository.saveAndFlush(userDB);
        }
        return null;
    }

    public User updateRoles(User user) {
        User userDB = getUser(user.getId());
        if (userDB != null) {
            user.getRoles().forEach(role -> {
                if (!userDB.getRoles().contains(role)) {
                    Role roleDB = getRole(role.getId());
                    userDB.getRoles().add(roleDB);
                }
            });
            userDB.getRoles().retainAll(user.getRoles());
            return userRepository.saveAndFlush(userDB);
        }
        return null;
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
