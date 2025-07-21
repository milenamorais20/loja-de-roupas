package sistema.gestao.vendas.loja.models.usuario;

import jakarta.validation.constraints.NotNull;
import sistema.gestao.vendas.loja.models.UserRole;

public record UsuarioRegisterDTO (
        @NotNull
        String nome,
        @NotNull
        String email,
        @NotNull
        String senha,
        @NotNull
        UserRole role

){
}
