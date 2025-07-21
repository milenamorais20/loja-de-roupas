package sistema.gestao.vendas.loja.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.gestao.vendas.loja.models.produto.Produto;
import sistema.gestao.vendas.loja.models.produto.ProdutoDTO;
import sistema.gestao.vendas.loja.repositories.ProdutoRepository;
import sistema.gestao.vendas.loja.services.ProdutoService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void criarProduto(ProdutoDTO produtoDTO) {
        Produto novoProduto = new Produto(produtoDTO.nome(), produtoDTO.tamanho(), produtoDTO.preco());
        novoProduto.setVendido(false);
        produtoRepository.save(novoProduto);
    }

    @Override
    public void atualizarProduto(Integer produtoId, ProdutoDTO produtoDTOAtualizado) {
        if(produtoRepository.existsById(produtoId)){

            Produto produtoAtualizado = produtoRepository.findById(produtoId)
                    .orElseThrow(()-> new EntityNotFoundException("Produto com id " + produtoId + " não encontrado"));

            produtoAtualizado.setNome(produtoDTOAtualizado.nome());
            produtoAtualizado.setTamanho(produtoDTOAtualizado.tamanho());
            produtoAtualizado.setPreco(produtoDTOAtualizado.preco());

            produtoRepository.save(produtoAtualizado);
        }else {
            throw new EntityNotFoundException("Não existe nenhum produto com esse id. Insira um válido");
        }

    }

    @Override
    public List<String> listaProdutosDisponiveis() {
        Iterator<Produto> iterator = produtoRepository.findAll().iterator();
        List<String> produtosDisponiveis =  new ArrayList<>();

        while (iterator.hasNext()){
            Produto produtoAtual = iterator.next();
            if(!produtoAtual.getVendido()){
                String produtoFormatado = "ID: "+ produtoAtual.getId() +" | Nome: " + produtoAtual.getNome() + " | Tamanho: "+ produtoAtual.getTamanho() + " | Preço: " + produtoAtual.getPreco();
                produtosDisponiveis.add(produtoFormatado);
            }
        }

        return produtosDisponiveis;
    }

    @Override
    public List<String> listaProdutos() {
        Iterator<Produto> iterator = produtoRepository.findAll().iterator();
        List<String> todosProdutos =  new ArrayList<>();

        while (iterator.hasNext()){
            Produto produtoAtual = iterator.next();
            String produtoFormatado = "Nome: " + produtoAtual.getNome() + " | Tamanho: "+ produtoAtual.getTamanho() + " | Preço: " + produtoAtual.getPreco() + " | Disponível: " + produtoAtual.getVendido();
            todosProdutos.add(produtoFormatado);
        }

        return todosProdutos;
    }

    @Override
    public Produto produtoById(Integer produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
    }

    @Override
    public void deletarProdutoById(Integer produtoId) {
        produtoRepository.deleteById(produtoId);
    }

    @Override
    public void deletarTodosProdutos() {
        produtoRepository.deleteAll();
    }
}
