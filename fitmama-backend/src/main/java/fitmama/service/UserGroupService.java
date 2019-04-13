package fitmama.service;

import fitmama.model.User;
import fitmama.model.UserGroup;
import fitmama.repo.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGroupService {

    @Autowired
    private UserGroupRepository groupRepository;

    public List<UserGroup> findAll() {
        return groupRepository.findAll();
    }

    public UserGroup findById(Long id) {
        Optional<UserGroup> userGroup = groupRepository.findById(id);

        if (userGroup.isPresent())  {
            return userGroup.get();
        }

        throw new RuntimeException("UserGroup with id=" + id + " does not exist");
    }

    public UserGroup save(UserGroup group) {
        return groupRepository.saveAndFlush(group);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
