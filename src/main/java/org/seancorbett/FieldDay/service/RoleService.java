package org.seancorbett.FieldDay.service;

import org.seancorbett.FieldDay.model.Role;

import java.util.List;

public interface RoleService {
    //CREATE
    public void saveRole(Role role);

    //READ
    public Role findRoleByRoleName(String name);
    public List<Role> getAllRoles();
    public List<Role> getRolesByUser(long id);
}
