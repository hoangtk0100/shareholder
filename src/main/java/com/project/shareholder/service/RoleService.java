package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Role;
import com.project.shareholder.request.RoleRequest;

import java.util.List;

public interface RoleService {
    // Create a new role
    Role create(RoleRequest roleRequest) throws DatabaseException;

    // Update specific role
    Role update(RoleRequest roleRequest) throws DatabaseException;

    // Deactivate role
    Role deactivate(RoleRequest roleRequest) throws DatabaseException;

    // Delete role
    Role delete(RoleRequest roleRequest) throws DatabaseException;

    // Retrieve role by name
    Role retrieveByName(String name) throws NotFoundException, DatabaseException;

    // Retrieve all role
    List<Role> list();
}
