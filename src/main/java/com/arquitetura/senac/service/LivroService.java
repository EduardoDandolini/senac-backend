package com.arquitetura.senac.service;

import com.arquitetura.senac.dto.LivroDto;
import com.arquitetura.senac.entity.Autor;
import com.arquitetura.senac.entity.Livro;
import com.arquitetura.senac.repository.LivroRepository;
import enuns.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro save(LivroDto dto) {
        Livro livro = Livro.builder()
                .nome(dto.nome())
               // .autor(Autor.builder().id(dto.autorId()).build())
                .editora(dto.editora())
                .genero(dto.genero())
                .status(Status.valueOf(dto.status().name()))
                .build();
        return repository.save(livro);
    }

    public Livro findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
    }

    public List<Livro> findAll() {
        return repository.findAll();
    }

    public Livro update(Long id, LivroDto dto) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        livro.setNome(dto.nome());
      //  livro.setAutor(Autor.builder().id(dto.autorId()).build());
        livro.setEditora(dto.editora());
        livro.setGenero(dto.genero());
        livro.setStatus(Status.valueOf(dto.status().name()));

        return repository.save(livro);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Livro não encontrado");
        }
    }
}
