package com.mycompany.pi.controller;

import com.mycompany.pi.model.Usuario;
import com.mycompany.pi.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest request) {
        return service.autenticar(
                request.email(),
                request.senha()
        );
    }

    public record LoginRequest(
            String email,
            String senha) {
    }
}