package sistema.gestao.vendas.loja.services;

import sistema.gestao.vendas.loja.models.usuario.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listaUsuarios();
    Usuario usuarioById(Integer usuarioId);
    void deletaUsuariorById(Integer usuarioId);
    void deletarTodosUsuarios();
    List<String> recibos(Integer usuarioId);
}
