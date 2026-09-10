package com.mycompany.pi.service;

import com.mycompany.pi.model.Usuario;
import com.mycompany.pi.repository.UsuarioRepository;

public class AuthService {

    private final UsuarioRepository repository;

    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario autenticar(String email, String senha) {

        if (email == null || email.isBlank()
                || senha == null || senha.isBlank()) {

            throw new IllegalArgumentException(
                    "E-mail e senha são obrigatórios.");
        }

        Usuario usuario = repository.buscarPorEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Usuário ou senha inválidos."));

        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException(
                    "Usuário ou senha inválidos.");
        }

        return usuario;
    }
}