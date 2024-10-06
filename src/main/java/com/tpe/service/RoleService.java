package com.tpe.service;

import com.tpe.entity.Role;
import com.tpe.entity.enums.UserRole;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByType(UserRole roleAdmin) {
        Role role = roleRepository.findByName(roleAdmin).orElseThrow(
                ()-> new ResourceNotFoundException("Role is not founded!!")
        );

        return role;
    }
}
