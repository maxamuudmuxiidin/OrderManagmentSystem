package com.OrderManagementSystem.service;

import com.OrderManagementSystem.model.Role;
import com.OrderManagementSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
