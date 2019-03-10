package fitmama.service;

import fitmama.model.UserGroup;
import fitmama.repo.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class UserGroupService {

    @Autowired
    private UserGroupRepository groupRepository;

    @Autowired
    private Scheduler dbScheduler;

    public Flux<UserGroup> findAll() {
        return Flux.fromIterable(groupRepository.findAll()).publishOn(dbScheduler);
    }

    public Mono<UserGroup> findById(Long id) {
        return Mono.just(groupRepository.findById(id).get()).publishOn(dbScheduler);
    }
}
