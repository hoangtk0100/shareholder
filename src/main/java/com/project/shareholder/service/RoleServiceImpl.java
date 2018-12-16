package com.project.shareholder.service;

import com.project.shareholder.dao.RoleDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Role;
import com.project.shareholder.request.RoleRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    // Create a new role
    @Override
    public Role create(RoleRequest roleRequest) throws DatabaseException {
        Role role = new Role();
        role.setName(roleRequest.getName());
        try {
            roleDao.createObj(role);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return role;
    }

    // Update role
    @Override
    public Role update(RoleRequest roleRequest) throws DatabaseException {
        Role role = new Role();
        role.setId(UUID.fromString(roleRequest.getId()));
        role.setName(roleRequest.getName());
        try {
            roleDao.updateObj(role);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return role;
    }

    // Deactivate role
    @Override
    public Role deactivate(RoleRequest roleRequest) throws DatabaseException {
        Role role = new Role();
        role.setId(UUID.fromString(roleRequest.getId()));
        try {
            roleDao.deactivateObj(role);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return role;
    }

    // Delete role
    @Override
    public String delete(String id) throws DatabaseException {
        Role role = new Role();
        role.setId(UUID.fromString(id));
        try {
            roleDao.deleteObj(role);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return id;
    }

    // Retrieve role by name
    @Override
    public Role retrieveByName(String name) throws NotFoundException {
        return roleDao.retrieveByName(name);
    }

    // Retrieve all role
    @Override
    public List<Role> list() {
        return roleDao.retrieveAll();
    }
}
