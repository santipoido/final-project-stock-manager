package com.tpfinal.stockmanager.dto;

import com.tpfinal.stockmanager.model.implementations.Role;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
@Builder
public class CreateUserRequest {
    private String username;
    private String password;
    private Role rol;
}

