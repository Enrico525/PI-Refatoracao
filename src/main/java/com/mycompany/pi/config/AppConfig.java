package com.mycompany.pi.config;

import com.mycompany.pi.model.Produto;
import com.mycompany.pi.model.Usuario;
import com.mycompany.pi.repository.*;
import com.mycompany.pi.service.AuthService;
import com.mycompany.pi.service.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ProdutoRepository produtoRepository() {

        InMemoryProdutoRepository repository =
                new InMemoryProdutoRepository();

        repository.salvar(
                new Produto(1, "Notebook", 3500.00, 5));

        repository.salvar(
                new Produto(2, "Mouse", 120.00, 20));

        repository.salvar(
                new Produto(3, "Teclado", 180.00, 15));

        return repository;
    }

    @Bean
    public ProdutoService produtoService(
            ProdutoRepository repository) {

        return new ProdutoService(repository);
    }

    @Bean
    public UsuarioRepository usuarioRepository() {

        InMemoryUsuarioRepository repository =
                new InMemoryUsuarioRepository();

        repository.salvar(
                new Usuario(
                        1,
                        "Administrador",
                        "admin@pi.com",
                        "123456"
                ));

        return repository;
    }

    @Bean
    public AuthService authService(
            UsuarioRepository repository) {

        return new AuthService(repository);
    }
}