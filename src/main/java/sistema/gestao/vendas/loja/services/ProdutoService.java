package sistema.gestao.vendas.loja.services;

import sistema.gestao.vendas.loja.models.produto.Produto;
import sistema.gestao.vendas.loja.models.produto.ProdutoDTO;

import java.util.List;

public interface ProdutoService {
    void criarProduto(ProdutoDTO produtoDTO);
    void atualizarProduto(Integer produtoId, ProdutoDTO produtoDTOAtualizado);
    List<String> listaProdutosDisponiveis();
    List<String> listaProdutos();
    Produto produtoById(Integer produtoId);
    void deletarProdutoById(Integer produtoId);
    void deletarTodosProdutos();
}
