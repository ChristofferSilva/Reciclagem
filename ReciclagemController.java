package com.reciclagem.controller;

import com.reciclagem.model.Reciclagem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reciclagem", produces = "application/json")
public class ReciclagemController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Reciclagem criar(@RequestBody Reciclagem r) {
        r.setId(1L);
        return r;
    }

    @GetMapping
    public List<Reciclagem> listar() {
        return List.of(new Reciclagem(1L, "PAPEL", 2.5));
    }

    @GetMapping("/{id}")
    public Reciclagem buscar(@PathVariable Long id) {
        return new Reciclagem(id, "PAPEL", 2.5);
    }

    @PutMapping("/{id}")
    public Reciclagem atualizar(@PathVariable Long id, @RequestBody Reciclagem r) {
        r.setId(id);
        return r;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        // mock delete
    }
}