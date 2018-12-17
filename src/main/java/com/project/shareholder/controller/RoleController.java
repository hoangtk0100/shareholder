package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Role;
import com.project.shareholder.request.RoleRequest;
import com.project.shareholder.service.RoleService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Create new role
    @PostMapping("/create")
    public Role create(@Valid @RequestBody RoleRequest roleRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return roleService.create(roleRequest);
    }

    // Deactivate role
    @PutMapping("/deactivate/{id}")
    public Role deactivate(@PathVariable String id) throws DatabaseException {
        return roleService.deactivate(id);
    }

    // Delete role
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws DatabaseException {
        return roleService.delete(id);
    }

    // Retrieve all roles
    @GetMapping("/all")
    public List<Role> retrieveAll() {
        return roleService.list();
    }

    // Retrieve role by id
    @GetMapping("/showById/{id}")
    public Role retrieveById(@PathVariable String id) throws NotFoundException {
        return roleService.retrieveById(id);
    }

    // Retrieve role by name
    @GetMapping("/showByName/{name}")
    public Role retrieveByName(@PathVariable String name) throws NotFoundException {
        return roleService.retrieveByName(name);
    }
}
