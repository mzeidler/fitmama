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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupJoinService {

    @Autowired
    private UserGroupJoinRepository groupJoinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository groupRepository;

    public List<UserGroupJoin> findByUserId(Long userId) {
        return groupJoinRepository.findByUser_Id(userId);
    }

    public List<UserGroupJoin> findByGroupId(Long groupId) {
        return groupJoinRepository.findByGroup_Id(groupId);
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
