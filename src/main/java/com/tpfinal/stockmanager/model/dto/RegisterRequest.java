package com.tpfinal.stockmanager.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String role;
}
