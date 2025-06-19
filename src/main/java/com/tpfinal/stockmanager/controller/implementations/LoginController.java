package com.tpfinal.stockmanager.controller.implementations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/api/auth/login")
    public String loginRedirect() {
        return "redirect:/login.html";
    }
}
