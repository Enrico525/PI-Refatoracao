package com.mycompany.pi.repository;

import com.mycompany.pi.model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProdutoRepository implements ProdutoRepository {

    private final List<Produto> produtos = new ArrayList<>();

    @Override
    public List<Produto> listar() {
        return new ArrayList<>(produtos);
    }

    @Override
    public Optional<Produto> buscarPorId(int id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    @Override
    public Produto salvar(Produto produto) {
        produtos.removeIf(p -> p.getId() == produto.getId());
        produtos.add(produto);
        return produto;
    }

    @Override
    public boolean excluir(int id) {
        return produtos.removeIf(p -> p.getId() == id);
    }
}