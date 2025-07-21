package sistema.gestao.vendas.loja.models.usuario;

import jakarta.validation.constraints.NotNull;

public record UsuarioLoginDTO(
        @NotNull
        String email,
        @NotNull
        String senha
){}
