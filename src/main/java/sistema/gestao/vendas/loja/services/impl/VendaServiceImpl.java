package sistema.gestao.vendas.loja.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.gestao.vendas.loja.models.produto.Produto;
import sistema.gestao.vendas.loja.models.usuario.Usuario;
import sistema.gestao.vendas.loja.models.venda.Venda;
import sistema.gestao.vendas.loja.models.venda.VendaDTO;
import sistema.gestao.vendas.loja.repositories.ProdutoRepository;
import sistema.gestao.vendas.loja.repositories.UsuarioRepository;
import sistema.gestao.vendas.loja.repositories.VendaRepository;
import sistema.gestao.vendas.loja.services.VendaService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VendaServiceImpl implements VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void criarVenda(VendaDTO vendaDTO) {

        Usuario usuario = usuarioRepository.findById(vendaDTO.usuarioId())
                .orElseThrow(()-> new EntityNotFoundException("Usuário com ID " + vendaDTO.usuarioId() + " não encontrado no banco de dados. "));

        List<Integer> listaIdProdutos = vendaDTO.produtosId();

        List<Produto>listaProdutos = new ArrayList<>();

        for(Integer produtoId : listaIdProdutos){
            Produto produto = produtoRepository.findById(produtoId)
                    .orElseThrow(()-> new EntityNotFoundException("Produto "+ produtoId + " não encontrado no banco de dados."));
            if (!produto.getVendido()){
                listaProdutos.add(produto);
                produto.setVendido(true);
            }else {
                throw new EntityNotFoundException("O prdoduto de ID " + produtoId + " não se encontra disponível.");
            }

        }

        Venda novaVenda = new Venda(usuario, new ArrayList<>());

        for(Produto produto : listaProdutos){
            produto.setVenda(novaVenda);
            novaVenda.getProdutos().add(produto);
        }


        vendaRepository.save(novaVenda);
        produtoRepository.saveAll(listaProdutos);
        usuario.getRecibos().add(novaVenda); // Aqui ele não vai sobreescrever o meu historico de ecibos. Ele vai pegar os recibos do usuario e vai adicionar a mpva venda.
        usuarioRepository.save(usuario);
    }

    @Override
    public List<String> listaVendas() {
        Iterator<Venda> iterator = vendaRepository.findAll().iterator();
        List<String> listaVendasFormatada = new ArrayList<>();

        while (iterator.hasNext()){
            Venda vendaAtual = iterator.next();
            List<Produto> produtoList = vendaAtual.getProdutos();
            List<String> itensList  = new ArrayList<>();

            for (Produto produto : produtoList){
                String item = "";
                item = " Produto: " + produto.getNome() + " - Tamanho: " + produto.getTamanho() + " - Preço: " +produto.getPreco() + " || ";
                itensList.add(item);
            }

            String vendaFormatada =
                    "Venda ID: " + vendaAtual.getId() + " | Nome cliente: " + vendaAtual.getUsuario().getNome() + " | Email do cliente: " + vendaAtual.getUsuario().getEmail() + " | " + itensList;

            listaVendasFormatada.add(vendaFormatada);
        }

        return listaVendasFormatada;
    }

    @Override
    public Venda vendaById(Integer vendaId) {
        return vendaRepository.findById(vendaId)
                .orElseThrow(()-> new EntityNotFoundException("Venda não encontrada."));
    }

    @Override
    public void deletarVenddaById(Integer vendaId) {
        vendaRepository.deleteById(vendaId);
    }

    @Override
    public void deletarTodasVendas() {
        vendaRepository.deleteAll();
    }
}
