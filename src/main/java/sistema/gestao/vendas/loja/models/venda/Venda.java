package sistema.gestao.vendas.loja.models.venda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sistema.gestao.vendas.loja.models.produto.Produto;
import sistema.gestao.vendas.loja.models.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

@Table(name = "venda")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "venda")
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    public Venda(Usuario usuario, List<Produto> produtos){
        this.usuario = usuario;
        this.produtos = produtos;
    }
}
