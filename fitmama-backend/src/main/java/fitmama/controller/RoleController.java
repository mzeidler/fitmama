package fitmama.controller;

import fitmama.model.Role;
import fitmama.model.Roles;
import fitmama.model.Trainings;
import fitmama.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/api/roles")
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/api/roles/short")
    public Roles findAllShort() {
        return new Roles(roleService.findAll());
    }
}
