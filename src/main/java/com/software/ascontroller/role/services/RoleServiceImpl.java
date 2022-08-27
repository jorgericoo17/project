package com.software.ascontroller.role.services;

import com.software.ascontroller.role.entities.Role;
import com.software.ascontroller.role.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
