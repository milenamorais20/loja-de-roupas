package sistema.gestao.vendas.loja.models.venda;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VendaDTO(
        @NotNull
        Integer usuarioId,
        @NotNull
        List<Integer> produtosId
) {
}
