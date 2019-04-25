package fitmama.service;

import fitmama.model.Role;
import fitmama.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role saveRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

}
