package com.mycompany.pi.service;

import com.mycompany.pi.model.Produto;
import com.mycompany.pi.repository.ProdutoRepository;
import java.util.List;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar() {
        return repository.listar();
    }

    public Produto buscar(int id) {
        return repository.buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Produto não encontrado."));
    }

    public Produto cadastrar(Produto produto) {
        validar(produto);

        if (repository.buscarPorId(produto.getId()).isPresent()) {
            throw new IllegalArgumentException(
                    "Já existe um produto com este ID.");
        }

        return repository.salvar(produto);
    }

    public Produto atualizar(Produto produto) {
        validar(produto);

        if (repository.buscarPorId(produto.getId()).isEmpty()) {
            throw new IllegalArgumentException(
                    "Produto não encontrado.");
        }

        return repository.salvar(produto);
    }

    public void excluir(int id) {
        if (!repository.excluir(id)) {
            throw new IllegalArgumentException(
                    "Produto não encontrado.");
        }
    }

    private void validar(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException(
                    "Produto obrigatório.");
        }

        if (produto.getId() <= 0) {
            throw new IllegalArgumentException(
                    "ID deve ser maior que zero.");
        }

        if (produto.getNome() == null ||
                produto.getNome().isBlank()) {

            throw new IllegalArgumentException(
                    "Nome é obrigatório.");
        }

        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException(
                    "Preço não pode ser negativo.");
        }

        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException(
                    "Quantidade não pode ser negativa.");
        }
    }
}