package sistema.gestao.vendas.loja.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistema.gestao.vendas.loja.infra.security.TokenService;
import sistema.gestao.vendas.loja.models.usuario.Usuario;
import sistema.gestao.vendas.loja.models.usuario.UsuarioLoginDTO;
import sistema.gestao.vendas.loja.models.usuario.UsuarioLoginResponseDTO;
import sistema.gestao.vendas.loja.models.usuario.UsuarioRegisterDTO;
import sistema.gestao.vendas.loja.repositories.UsuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponseDTO> login(@Valid @RequestBody UsuarioLoginDTO usuarioLoginDTO){

//        VERIFICA SE CHAMA O AUTENTICATIOSERVICE
//        System.out.println("EMAIL: " + usuarioLoginDTO.email());
//        System.out.println("SENHA: " + usuarioLoginDTO.senha());
//
//        UserDetails user = usuarioRepository.findByEmail(usuarioLoginDTO.email());
//        System.out.println("Usuário encontrado? " + (user != null));
//        System.out.println("Senha no banco: " + user.getPassword());
//        System.out.println("Senha confere? " + new BCryptPasswordEncoder().matches(usuarioLoginDTO.senha(), user.getPassword()));

        var usernamePassword =  new UsernamePasswordAuthenticationToken(usuarioLoginDTO.email(), usuarioLoginDTO.senha());

        var auth = authenticationManager.authenticate(usernamePassword);

        Usuario usuario =(Usuario) auth.getPrincipal();
        var token = tokenService.generateToken(usuario.getEmail(), usuario.getRole().name());

        return ResponseEntity.ok(new UsuarioLoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UsuarioRegisterDTO usuarioRegisterDTO){

        if (usuarioRepository.findByEmail(usuarioRegisterDTO.email()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encriptedPassword = new BCryptPasswordEncoder().encode(usuarioRegisterDTO.senha());
        Usuario usuario =  new Usuario(usuarioRegisterDTO.nome(), usuarioRegisterDTO.email(), encriptedPassword, usuarioRegisterDTO.role());

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário salvo com sucesso!");
    }
}
