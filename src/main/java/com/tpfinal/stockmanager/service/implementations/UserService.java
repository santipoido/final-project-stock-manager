package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.model.implementations.Role;
import com.tpfinal.stockmanager.model.implementations.User;
import com.tpfinal.stockmanager.repository.interfaces.ProductRepository;
import com.tpfinal.stockmanager.repository.interfaces.RoleRepository;
import com.tpfinal.stockmanager.repository.interfaces.UserRepository;
import com.tpfinal.stockmanager.service.interfaces.IntUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IntUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOptionalById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
    }

    @Override
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public User update(Integer id, User entityDetails) {
        User existingEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        existingEntity.setUsername(entityDetails.getUsername());
        existingEntity.setPassw(entityDetails.getPassw());
        existingEntity.setRole(entityDetails.getRole());

        return userRepository.save(existingEntity);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
