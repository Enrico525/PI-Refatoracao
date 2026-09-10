package com.mycompany.pi;

import com.mycompany.pi.model.Produto;
import com.mycompany.pi.model.Usuario;
import com.mycompany.pi.repository.InMemoryProdutoRepository;
import com.mycompany.pi.repository.InMemoryUsuarioRepository;
import com.mycompany.pi.service.AuthService;
import com.mycompany.pi.service.ProdutoService;

public class MainTestes {

    public static void main(String[] args) {

        testarProdutos();

        testarLogin();

        System.out.println();
        System.out.println("TODOS OS TESTES PASSARAM.");
    }

    private static void testarProdutos() {

        ProdutoService service =
                new ProdutoService(
                        new InMemoryProdutoRepository());

        service.cadastrar(
                new Produto(
                        1,
                        "Notebook",
                        3500.00,
                        5));

        service.cadastrar(
                new Produto(
                        2,
                        "Mouse",
                        120.00,
                        10));

        assert service.listar().size() == 2;

        assert service.buscar(1)
                .getNome()
                .equals("Notebook");

        service.atualizar(
                new Produto(
                        1,
                        "Notebook Gamer",
                        4500.00,
                        3));

        assert service.buscar(1)
                .getNome()
                .equals("Notebook Gamer");

        service.excluir(2);

        assert service.listar().size() == 1;

        System.out.println(
                "Testes de Produto: OK");
    }

    private static void testarLogin() {

        InMemoryUsuarioRepository repository =
                new InMemoryUsuarioRepository();

        repository.salvar(
                new Usuario(
                        1,
                        "Administrador",
                        "admin@pi.com",
                        "123456"));

        AuthService authService =
                new AuthService(repository);

        Usuario usuario =
                authService.autenticar(
                        "admin@pi.com",
                        "123456");

        assert usuario.getNome()
                .equals("Administrador");

        try {

            authService.autenticar(
                    "admin@pi.com",
                    "senhaerrada");

            throw new AssertionError(
                    "Login inválido foi aceito.");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Teste de login inválido: OK");
        }

        System.out.println(
                "Testes de Login: OK");
    }
}