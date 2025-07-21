package sistema.gestao.vendas.loja.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.gestao.vendas.loja.models.produto.Produto;
import sistema.gestao.vendas.loja.models.usuario.Usuario;
import sistema.gestao.vendas.loja.models.venda.Venda;
import sistema.gestao.vendas.loja.repositories.UsuarioRepository;
import sistema.gestao.vendas.loja.repositories.VendaRepository;
import sistema.gestao.vendas.loja.services.UsuarioService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VendaRepository vendaRepository;

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario usuarioById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new EntityNotFoundException("Usuario não encontrado."));
    }

    @Override
    public void deletaUsuariorById(Integer usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public void deletarTodosUsuarios() {
        usuarioRepository.deleteAll();
    }

    @Override
    public List<String> recibos(Integer usuarioId) {
        Iterator<Venda> iterator = vendaRepository.findAll().iterator();

        List<String> listaRecibos = new ArrayList<>();

        while (iterator.hasNext()){
            String vendaFormatada = "";
            Venda vendaAtual = iterator.next();

            if(Objects.equals(usuarioId, vendaAtual.getUsuario().getId())){
                List<Produto> produtoList = vendaAtual.getProdutos();

                List<String> itensList  = new ArrayList<>();

                for (Produto produto : produtoList){
                    String item = "";
                    item = "Produto: " + produto.getNome() + " - Tamanho" + produto.getTamanho() + " - Preço: " +produto.getPreco();
                    itensList.add(item);
                }

                vendaFormatada = "ID da venda: " + vendaAtual.getId() + "| Cliente: " + vendaAtual.getUsuario().getNome() + "| Produtos: " + itensList;
                listaRecibos.add(vendaFormatada);
            }
        }

        return listaRecibos;
    }
}
