package org.seancorbett.FieldDay.service.impl;

import org.seancorbett.FieldDay.model.Role;
import org.seancorbett.FieldDay.repository.RoleRepository;
import org.seancorbett.FieldDay.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role findRoleByRoleName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public List<Role> getRolesByUser(long id) {
        return roleRepository.findRoleByUser(id);
    }

}
