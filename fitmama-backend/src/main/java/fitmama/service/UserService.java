package fitmama.service;

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
}
