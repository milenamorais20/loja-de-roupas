package sistema.gestao.vendas.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.gestao.vendas.loja.models.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
