package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.model.implementations.User;
import com.tpfinal.stockmanager.repository.interfaces.CategoryRepository;
import com.tpfinal.stockmanager.repository.interfaces.UserRepository;
import com.tpfinal.stockmanager.service.interfaces.IntUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IntUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOptionalById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findById(Integer id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    @Override
    public Optional<User> findOptionalByName(String username) {
        return userRepository.findByusername(username);
    }

    @Override
    public User create(User entity) throws EntityNotFoundException {
        if(!userRepository.existsById(entity.getId())) {
            return userRepository.save(entity);

        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    @Transactional
    public User update(Integer id, User entityDetails) throws EntityNotFoundException {
        User existingEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));

        existingEntity.setUsername(entityDetails.getUsername());
        existingEntity.setPassword(entityDetails.getPassword());

        return userRepository.save(existingEntity);
    }

    @Override
    public void delete(Integer id) throws EntityNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " was not found"));

        userRepository.delete(user);
    }
}
