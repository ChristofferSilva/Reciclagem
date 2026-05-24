package com.reciclagem.controller;

import com.reciclagem.model.Reciclagem;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reciclagem")
public class ReciclagemController {

    private List<Reciclagem> lista = new ArrayList<>();

    // GET ALL
    @GetMapping
    public List<Reciclagem> listar() {
        return lista;
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Reciclagem buscar(@PathVariable Long id) {

        Optional<Reciclagem> resultado = lista.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();

        return resultado.orElse(null);
    }

    // POST
    @PostMapping
    public Reciclagem salvar(@RequestBody Reciclagem reciclagem) {

        lista.add(reciclagem);

        return reciclagem;
    }

    // PUT
    @PutMapping("/{id}")
    public Reciclagem atualizar(
            @PathVariable Long id,
            @RequestBody Reciclagem reciclagemAtualizada) {

        for (Reciclagem reciclagem : lista) {

            if (reciclagem.getId().equals(id)) {

                reciclagem.setTipo(reciclagemAtualizada.getTipo());
                reciclagem.setPeso(reciclagemAtualizada.getPeso());

                return reciclagem;
            }
        }

        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {

        lista.removeIf(r -> r.getId().equals(id));

        return "Deletado com sucesso";
    }
}