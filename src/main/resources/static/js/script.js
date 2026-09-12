/* =========================================
   DADOS DOS PRODUTOS
========================================= */

let produtos = JSON.parse(
    localStorage.getItem("produtos")
) || [
    {
        id: 1,
        nome: "Notebook",
        preco: 3500.00,
        quantidade: 8
    },
    {
        id: 2,
        nome: "Mouse",
        preco: 120.00,
        quantidade: 25
    },
    {
        id: 3,
        nome: "Teclado",
        preco: 180.00,
        quantidade: 3
    },
    {
        id: 4,
        nome: "Monitor",
        preco: 950.00,
        quantidade: 0
    }
];


/* =========================================
   SALVAR PRODUTOS
========================================= */

function salvarProdutos() {

    localStorage.setItem(
        "produtos",
        JSON.stringify(produtos)
    );

}


/* =========================================
   FORMATAR MOEDA
========================================= */

function formatarMoeda(valor) {

    return valor.toLocaleString(
        "pt-BR",
        {
            style: "currency",
            currency: "BRL"
        }
    );

}


/* =========================================
   STATUS DO ESTOQUE
========================================= */

function obterStatus(quantidade) {

    if (quantidade === 0) {

        return `
            <span class="status status-empty">
                Sem estoque
            </span>
        `;

    }

    if (quantidade <= 5) {

        return `
            <span class="status status-low">
                Estoque baixo
            </span>
        `;

    }

    return `
        <span class="status status-ok">
            Disponível
        </span>
    `;

}


/* =========================================
   DASHBOARD
========================================= */

function carregarDashboard() {

    const totalProdutos =
        document.getElementById("totalProdutos");

    const valorEstoque =
        document.getElementById("valorEstoque");

    const estoqueBaixo =
        document.getElementById("estoqueBaixo");

    const tabela =
        document.getElementById("tabelaDashboard");


    if (!totalProdutos) {
        return;
    }


    totalProdutos.textContent =
        produtos.length;


    const valorTotal =
        produtos.reduce(
            (total, produto) =>
                total +
                (produto.preco * produto.quantidade),
            0
        );


    valorEstoque.textContent =
        formatarMoeda(valorTotal);


    const produtosBaixo =
        produtos.filter(
            produto => produto.quantidade <= 5
        );


    estoqueBaixo.textContent =
        produtosBaixo.length;


    tabela.innerHTML = "";


    produtos.slice(0, 5).forEach(produto => {

        const linha =
            document.createElement("tr");


        linha.innerHTML = `

            <td>${produto.id}</td>

            <td>
                <strong>
                    ${produto.nome}
                </strong>
            </td>

            <td>
                ${formatarMoeda(produto.preco)}
            </td>

            <td>
                ${produto.quantidade}
            </td>

            <td>
                ${obterStatus(produto.quantidade)}
            </td>

        `;


        tabela.appendChild(linha);

    });

}


/* =========================================
   LISTAGEM DE PRODUTOS
========================================= */

function carregarProdutos(filtro = "") {

    const tabela =
        document.getElementById("tabelaProdutos");


    if (!tabela) {
        return;
    }


    tabela.innerHTML = "";


    const produtosFiltrados =
        produtos.filter(produto =>
            produto.nome
                .toLowerCase()
                .includes(
                    filtro.toLowerCase()
                )
        );


    produtosFiltrados.forEach(produto => {

        const linha =
            document.createElement("tr");


        linha.innerHTML = `

            <td>${produto.id}</td>

            <td>
                <strong>
                    ${produto.nome}
                </strong>
            </td>

            <td>
                ${formatarMoeda(produto.preco)}
            </td>

            <td>
                ${produto.quantidade}
            </td>

            <td>
                ${obterStatus(produto.quantidade)}
            </td>

            <td>

                <button
                    class="btn btn-danger"
                    onclick="excluirProduto(${produto.id})"
                >
                    Excluir
                </button>

            </td>

        `;


        tabela.appendChild(linha);

    });


    if (produtosFiltrados.length === 0) {

        tabela.innerHTML = `

            <tr>

                <td
                    colspan="6"
                    style="text-align:center;"
                >
                    Nenhum produto encontrado.
                </td>

            </tr>

        `;

    }

}


/* =========================================
   EXCLUIR PRODUTO
========================================= */

function excluirProduto(id) {

    const produto =
        produtos.find(
            produto => produto.id === id
        );


    if (!produto) {
        return;
    }


    const confirmar =
        confirm(
            `Deseja realmente excluir "${produto.nome}"?`
        );


    if (!confirmar) {
        return;
    }


    produtos =
        produtos.filter(
            produto => produto.id !== id
        );


    salvarProdutos();

    carregarProdutos();

}


/* =========================================
   PESQUISA
========================================= */

const pesquisa =
    document.getElementById(
        "pesquisaProduto"
    );


if (pesquisa) {

    pesquisa.addEventListener(
        "input",
        function () {

            carregarProdutos(
                this.value
            );

        }
    );

}


/* =========================================
   CADASTRO DE PRODUTO
========================================= */

const form =
    document.getElementById(
        "formProduto"
    );


if (form) {

    form.addEventListener(
        "submit",
        function (event) {

            event.preventDefault();


            const id =
                Number(
                    document.getElementById(
                        "idProduto"
                    ).value
                );


            const nome =
                document.getElementById(
                    "nomeProduto"
                ).value.trim();


            const preco =
                Number(
                    document.getElementById(
                        "precoProduto"
                    ).value
                );


            const quantidade =
                Number(
                    document.getElementById(
                        "quantidadeProduto"
                    ).value
                );


            const mensagem =
                document.getElementById(
                    "mensagem"
                );


            /* =========================
               VALIDAÇÕES
            ========================= */

            if (id <= 0) {

                mostrarMensagem(
                    mensagem,
                    "O ID deve ser maior que zero.",
                    "erro"
                );

                return;

            }


            if (nome.length < 2) {

                mostrarMensagem(
                    mensagem,
                    "O nome do produto deve possuir pelo menos 2 caracteres.",
                    "erro"
                );

                return;

            }


            if (preco <= 0) {

                mostrarMensagem(
                    mensagem,
                    "O preço deve ser maior que zero.",
                    "erro"
                );

                return;

            }


            if (quantidade < 0) {

                mostrarMensagem(
                    mensagem,
                    "A quantidade não pode ser negativa.",
                    "erro"
                );

                return;

            }


            const idExistente =
                produtos.some(
                    produto =>
                        produto.id === id
                );


            if (idExistente) {

                mostrarMensagem(
                    mensagem,
                    "Já existe um produto com esse ID.",
                    "erro"
                );

                return;

            }


            /* =========================
               CADASTRO
            ========================= */

            const novoProduto = {

                id: id,

                nome: nome,

                preco: preco,

                quantidade: quantidade

            };


            produtos.push(
                novoProduto
            );


            salvarProdutos();


            mostrarMensagem(
                mensagem,
                "Produto cadastrado com sucesso!",
                "sucesso"
            );


            form.reset();


            setTimeout(
                function () {

                    window.location.href =
                        "produtos.html";

                },
                1000
            );

        }
    );

}


/* =========================================
   MENSAGENS
========================================= */

function mostrarMensagem(
    elemento,
    texto,
    tipo
) {

    elemento.textContent =
        texto;


    elemento.className =
        "mensagem " + tipo;

}


/* =========================================
   INICIALIZAÇÃO
========================================= */

carregarDashboard();

carregarProdutos();
