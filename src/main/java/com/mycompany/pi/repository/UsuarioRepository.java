package com.mycompany.pi.repository;

import com.mycompany.pi.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> buscarPorEmail(String email);

    void salvar(Usuario usuario);

    List<Usuario> listar();
}