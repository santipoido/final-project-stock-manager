package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.model.implementations.User;
import com.tpfinal.stockmanager.repository.interfaces.ProductRepository;
import com.tpfinal.stockmanager.repository.interfaces.RoleRepository;
import com.tpfinal.stockmanager.repository.interfaces.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteProduct(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }
}
