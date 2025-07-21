package sistema.gestao.vendas.loja.models.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sistema.gestao.vendas.loja.models.venda.Venda;

@Table(name = "produto")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Character tamanho;
    private Double preco;
    private Boolean vendido;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    public Produto(String nome, Character tamanho, Double preco){
        this.nome = nome;
        this.tamanho = tamanho;
        this.preco = preco;
    }

}
