

import com.mycompany.pi.model.Produto;
import com.mycompany.pi.repository.InMemoryProdutoRepository;
import com.mycompany.pi.service.ProdutoService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProdutoServiceTest {

    @Test
    void deveCalcularValorTotalDoEstoque() {

        ProdutoService service =
                new ProdutoService(
                        new InMemoryProdutoRepository());

        Produto produto =
                new Produto(
                        1,
                        "Notebook",
                        3500.00,
                        5);

        double resultado =
                service.calcularValorTotalEstoque(produto);

        assertEquals(
                17500.00,
                resultado,
                0.001);
    }

    @Test
    void deveRejeitarProdutoComPrecoNegativo() {

        ProdutoService service =
                new ProdutoService(
                        new InMemoryProdutoRepository());

        Produto produto =
                new Produto(
                        1,
                        "Notebook",
                        -100.00,
                        5);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularValorTotalEstoque(produto));
    }
}