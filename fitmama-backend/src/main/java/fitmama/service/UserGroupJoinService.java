package fitmama.service;

import fitmama.model.Measurement;
import fitmama.model.UserGroupJoin;
import fitmama.repo.UserGroupJoinRepository;
import fitmama.repo.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

@Service
public class UserGroupJoinService {

    @Autowired
    private UserGroupJoinRepository groupJoinRepository;

    @Autowired
    private Scheduler dbScheduler;

    public Flux<UserGroupJoin> findByUserId(Long userId) {
        return Flux.fromIterable(groupJoinRepository.findByUser_Id(userId)).publishOn(dbScheduler);
    }

    public Flux<UserGroupJoin> findByGroupId(Long groupId) {
        return Flux.fromIterable(groupJoinRepository.findByGroup_Id(groupId)).publishOn(dbScheduler);
    }

}
