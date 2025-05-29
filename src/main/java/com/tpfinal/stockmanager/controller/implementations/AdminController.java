package com.tpfinal.stockmanager.controller.implementations;

import com.tpfinal.stockmanager.dto.CreateUserRequest;
import com.tpfinal.stockmanager.model.implementations.User;
import com.tpfinal.stockmanager.service.implementations.RegisterUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final RegisterUserService registroUsuarioService;

    public AdminController(RegisterUserService service) {
        this.registroUsuarioService = service;
    }

    @PostMapping("/create-user")
    public String crearUsuario(@RequestBody CreateUserRequest req) {
        User nuevo = registroUsuarioService.registrarNuevoUsuario(req);
        return "User create with ID: " + nuevo.getId();
    }
}

