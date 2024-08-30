package com.arquitetura.senac.controller;

import com.arquitetura.senac.dto.EmprestimoDto;
import com.arquitetura.senac.entity.Emprestimo;
import com.arquitetura.senac.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private final EmprestimoService service;
    @PostMapping("/salvar")
    public ResponseEntity<Emprestimo> save(@RequestBody EmprestimoDto dto) {
        return ResponseEntity.ok(service.emprestarLivro(dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Emprestimo>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Emprestimo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Emprestimo> update(@PathVariable Long id, @RequestBody EmprestimoDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
