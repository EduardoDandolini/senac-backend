package com.arquitetura.senac.controller;

import com.arquitetura.senac.dto.UsuarioRequest;
import com.arquitetura.senac.dto.UsuarioResponse;
import com.arquitetura.senac.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse save(@RequestBody UsuarioRequest request) {
       return service.save(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse update(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        return service.update(id, request);
    }
}
