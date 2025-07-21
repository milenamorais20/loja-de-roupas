package sistema.gestao.vendas.loja.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sistema.gestao.vendas.loja.models.venda.Venda;
import sistema.gestao.vendas.loja.models.venda.VendaDTO;
import sistema.gestao.vendas.loja.services.VendaService;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @PreAuthorize("hasAnyRole('FUNCIONARIO', 'CLIENTE')")
    @PostMapping("/criar")
    public ResponseEntity<String> create(@Valid @RequestBody VendaDTO vendaDTO){
        vendaService.criarVenda(vendaDTO);
        return ResponseEntity.ok("Venda feita com sucesso! ");
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAll(){
        return ResponseEntity.ok(vendaService.listaVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(vendaService.vendaById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        vendaService.deletarVenddaById(id);
        return ResponseEntity.ok("Venda deletado com sucesso.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll(){
        vendaService.deletarTodasVendas();
        return ResponseEntity.ok("Vendas deletadas com sucesso.");

    }

}
