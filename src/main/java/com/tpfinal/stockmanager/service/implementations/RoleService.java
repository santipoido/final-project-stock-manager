package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.model.implementations.Role;
import com.tpfinal.stockmanager.repository.interfaces.ProductRepository;
import com.tpfinal.stockmanager.repository.interfaces.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById(id);
    }
}
