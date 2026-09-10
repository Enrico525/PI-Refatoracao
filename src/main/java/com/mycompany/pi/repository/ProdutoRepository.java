package com.mycompany.pi.repository;

import com.mycompany.pi.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    List<Produto> listar();

    Optional<Produto> buscarPorId(int id);

    Produto salvar(Produto produto);

    boolean excluir(int id);
}