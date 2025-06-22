package com.tpfinal.stockmanager.controller.implementations;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Login")
@Controller
public class LoginController {
    @GetMapping("/api/auth/login")
    public String loginRedirect() {
        return "redirect:/login.html";
    }
}
