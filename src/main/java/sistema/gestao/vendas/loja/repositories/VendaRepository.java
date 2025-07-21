package sistema.gestao.vendas.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.gestao.vendas.loja.models.venda.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
}
