package com.tpfinal.stockmanager.model.dto;

import com.tpfinal.stockmanager.exception.ValidRole;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "The username can't be empty")
    @Size(min = 4, message = "The username must be at least 4 characters")
    private String username;

    @NotBlank(message = "The password can't be empty")
    @Size(min = 6, message = "The password must be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "The password must contain at least one uppercase letter and one number"
    )
    private String password;

    @NotBlank(message = "The name can't be empty")
    @Size(min = 3, message = "The name must be at least 3 characters")
    private String name;

    @Size(min = 3, message = "The lastname must be at least 4 characters")
    @NotBlank(message = "The lastname can't be empty")
    private String lastname;

    @NotBlank(message = "The role can't be empty")
    @ValidRole
    private String role;
}

