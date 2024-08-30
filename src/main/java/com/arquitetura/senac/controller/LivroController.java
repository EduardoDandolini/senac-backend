package com.arquitetura.senac.controller;

import com.arquitetura.senac.dto.LivroDto;
import com.arquitetura.senac.entity.Livro;
import com.arquitetura.senac.service.LivroService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @PostMapping("/salvar")
    public ResponseEntity<Livro> save(@RequestBody LivroDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody LivroDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exportar")
    public ResponseEntity<Void> gerarPlanilha(HttpServletResponse response) throws IOException {
        service.gerarPlanilha(response);
        return ResponseEntity.ok().build();
    }

}
