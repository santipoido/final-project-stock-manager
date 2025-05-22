package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.model.implementations.Role;
import com.tpfinal.stockmanager.repository.interfaces.ProductRepository;
import com.tpfinal.stockmanager.repository.interfaces.RoleRepository;
import com.tpfinal.stockmanager.service.interfaces.IntRoleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IntRoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findOptionalById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
    }

    @Override
    public Role create(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    @Transactional
    public Role update(Integer id, Role entityDetails) {
        Role existingEntity = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        existingEntity.setRoleName(entityDetails.getRoleName());
        existingEntity.setRoleDescription(entityDetails.getRoleDescription());
        existingEntity.setUsers(entityDetails.getUsers());

        return roleRepository.save(existingEntity);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
