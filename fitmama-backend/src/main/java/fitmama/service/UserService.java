package fitmama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitmama.model.User;
import fitmama.repo.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Scheduler dbScheduler;

    public Flux<User> findAll() {
        return Flux.fromIterable(userRepository.findAll()).publishOn(dbScheduler);
    }

    public Mono<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return Mono.just(userRepository.findById(id).get()).publishOn(dbScheduler);
        }
        throw new RuntimeException("User with id=" + id + " does not exist");
    }

    public Mono<User> saveUser(User user) {
        return Mono.fromCallable(() -> userRepository.saveAndFlush(user)).publishOn(dbScheduler);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
