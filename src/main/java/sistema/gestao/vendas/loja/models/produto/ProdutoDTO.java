package sistema.gestao.vendas.loja.models.produto;

import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
        @NotNull
        String nome,
        @NotNull
        Character tamanho,
        @NotNull
        Double preco
) {
}
