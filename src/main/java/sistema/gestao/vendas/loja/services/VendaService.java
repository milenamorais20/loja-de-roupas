package sistema.gestao.vendas.loja.services;

import sistema.gestao.vendas.loja.models.venda.Venda;
import sistema.gestao.vendas.loja.models.venda.VendaDTO;

import java.util.List;

public interface VendaService {
    void criarVenda(VendaDTO vendaDTO);
    List<String> listaVendas();
    Venda vendaById(Integer vendaId);
    void deletarVenddaById(Integer vendaId);
    void deletarTodasVendas();
}
