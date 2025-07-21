package sistema.gestao.vendas.loja.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sistema.gestao.vendas.loja.models.produto.Produto;
import sistema.gestao.vendas.loja.models.produto.ProdutoDTO;
import sistema.gestao.vendas.loja.services.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PreAuthorize("hasRole('FUNCIONARIO')")
    @PostMapping("/criar")
    public ResponseEntity<String> create(@Valid @RequestBody ProdutoDTO produtoDTOAtualizado){
        produtoService.criarProduto(produtoDTOAtualizado);
        return ResponseEntity.ok("Produto criado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer produtoId, @Valid @RequestBody ProdutoDTO produtoDTOAtualizado){
        produtoService.atualizarProduto(produtoId, produtoDTOAtualizado);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAll(){
        return ResponseEntity.ok(produtoService.listaProdutos());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<String>> getDispoiveis(){
        return ResponseEntity.ok(produtoService.listaProdutosDisponiveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(produtoService.produtoById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        produtoService.deletarProdutoById(id);
        return ResponseEntity.ok("O produto do id "+id+" foi deletado");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll(){
        produtoService.deletarTodosProdutos();
        return ResponseEntity.ok("Todos os produtos foram deletados");
    }
}
