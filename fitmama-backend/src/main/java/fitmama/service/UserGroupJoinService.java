package fitmama.service;

import fitmama.model.Measurement;
import fitmama.model.User;
import fitmama.model.UserGroup;
import fitmama.model.UserGroupJoin;
import fitmama.repo.UserGroupJoinRepository;
import fitmama.repo.UserGroupRepository;
import fitmama.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.security.acl.Group;
import java.util.Date;
import java.util.Optional;

@Service
public class UserGroupJoinService {

    @Autowired
    private UserGroupJoinRepository groupJoinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository groupRepository;

    @Autowired
    private Scheduler dbScheduler;

    public Flux<UserGroupJoin> findByUserId(Long userId) {
        return Flux.fromIterable(groupJoinRepository.findByUser_Id(userId)).publishOn(dbScheduler);
    }

    public Flux<UserGroupJoin> findByGroupId(Long groupId) {
        return Flux.fromIterable(groupJoinRepository.findByGroup_Id(groupId)).publishOn(dbScheduler);
    }

    public void join(Long userId, Long groupId) {

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<UserGroup> groupOpt = groupRepository.findById(groupId);

        if (!userOpt.isPresent()) {
            throw new RuntimeException("The User with Id=" + userId + " does not exist");
        }

        if (!groupOpt.isPresent()) {
            throw new RuntimeException("The Group with Id=" + groupId + " does not exist");
        }

        Optional<UserGroupJoin> joinOpt = groupJoinRepository.findByUser_IdAndGroup_Id(userId, groupId);
        if (!joinOpt.isPresent()) {
            UserGroupJoin join = new UserGroupJoin();
            join.setUser(userOpt.get());
            join.setGroup(groupOpt.get());
            join.setJoined(new Date());
            groupJoinRepository.save(join);
        }
    }

    public void leave(Long userId, Long groupId) {
        Optional<UserGroupJoin> joinOpt = groupJoinRepository.findByUser_IdAndGroup_Id(userId, groupId);
        if (joinOpt.isPresent()) {
            groupJoinRepository.delete(joinOpt.get());
        }
    }
}
