package com.arquitetura.senac.controller;

import com.arquitetura.senac.dto.AutorDto;
import com.arquitetura.senac.entity.Autor;
import com.arquitetura.senac.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;

    @PostMapping("/salvar")
    public ResponseEntity<Autor> save(@RequestBody AutorDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Autor>>  findAll(){
        return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Autor> findById(@PathVariable long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

  @PutMapping("/atualizar/{id}")
    public ResponseEntity<Autor> update(@PathVariable long id, @RequestBody AutorDto dto){
        return ResponseEntity.ok(service.Update(id,dto));
  }

  @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
  }
}
