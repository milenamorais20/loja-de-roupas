package sistema.gestao.vendas.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.gestao.vendas.loja.models.usuario.Usuario;
import sistema.gestao.vendas.loja.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(usuarioService.listaUsuarios());
    }

    @GetMapping("/recibos/{id}")
    public List<String> getRecibos(@PathVariable("id") Integer usuarioId){
        return usuarioService.recibos(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id){
        usuarioService.usuarioById(id);
        return ResponseEntity.ok(usuarioService.usuarioById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        usuarioService.deletaUsuariorById(id);
        return ResponseEntity.ok("O usuario do id "+id+" foi deletado");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll(){
        usuarioService.deletarTodosUsuarios();
        return ResponseEntity.ok("Todos os usuarios foram deletados");
    }
}
