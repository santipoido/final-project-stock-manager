package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.dto.CreateUserRequest;
import com.tpfinal.stockmanager.model.implementations.User;
import com.tpfinal.stockmanager.repository.interfaces.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public RegisterUserService(UserRepository repo, PasswordEncoder encoder) {
        this.userRepository = repo;
        this.encoder = encoder;
    }

    public User registrarNuevoUsuario(CreateUserRequest req) {
        User nuevo = User.builder()
                .username(req.getUsername())
                .passw(encoder.encode(req.getPassword()))
                .role(req.getRol())
                .build();
        return userRepository.save(nuevo);
    }
}
